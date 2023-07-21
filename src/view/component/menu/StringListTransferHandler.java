package view.component.menu;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.util.List;

public class StringListTransferHandler extends TransferHandler {
    @Override
    public int getSourceActions(JComponent c) {
        return MOVE; // Разрешаем только операцию перемещения элементов
    }

    @Override
    protected Transferable createTransferable(JComponent c) {
        JList<String> sourceList = (JList<String>) c;
        List<String> selectedValues = sourceList.getSelectedValuesList();
        StringBuilder data = new StringBuilder();
        for (String value : selectedValues) {
            data.append(value).append("\n");
        }
        return new StringTransferable(data.toString());
    }

    @Override
    public boolean canImport(TransferSupport support) {
        return support.isDataFlavorSupported(DataFlavor.stringFlavor);
    }

    @Override
    public boolean importData(TransferSupport support) {
        if (!canImport(support)) {
            return false;
        }

        Transferable transferable = support.getTransferable();
        try {
            String data = (String) transferable.getTransferData(DataFlavor.stringFlavor);
            JList.DropLocation dropLocation = (JList.DropLocation) support.getDropLocation();
            int index = dropLocation.getIndex();
            JList<String> targetList = (JList<String>) support.getComponent();
            DefaultListModel<String> model = (DefaultListModel<String>) targetList.getModel();

            String[] itemsToAdd = data.split("\n");
            for (String item : itemsToAdd) {
                if (!item.isEmpty()) {
                    model.add(index++, item);
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private static class StringTransferable implements Transferable {
        private final String data;

        public StringTransferable(String data) {
            this.data = data;
        }

        @Override
        public DataFlavor[] getTransferDataFlavors() {
            return new DataFlavor[]{DataFlavor.stringFlavor};
        }

        @Override
        public boolean isDataFlavorSupported(DataFlavor flavor) {
            return flavor.equals(DataFlavor.stringFlavor);
        }

        @Override
        public Object getTransferData(DataFlavor flavor) {
            return data;
        }
    }
}

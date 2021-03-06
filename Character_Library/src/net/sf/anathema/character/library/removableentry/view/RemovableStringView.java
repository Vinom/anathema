package net.sf.anathema.character.library.removableentry.view;

import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.sf.anathema.character.library.removableentry.presenter.IRemovableEntryView;

public class RemovableStringView implements IRemovableEntryView {

  private final JButton button;
  private final JLabel label;
  private JPanel contentPanel;

  public RemovableStringView(Icon removeIcon, String string) {
    this.label = new JLabel(string);
    this.button = new JButton(removeIcon);
    button.setPreferredSize(new Dimension(removeIcon.getIconWidth() + 4, removeIcon.getIconHeight() + 4));
  }

  public void addContent(JPanel panel) {
    this.contentPanel = panel;
    panel.add(label, GridDialogLayoutData.FILL_HORIZONTAL);
    panel.add(button);
    panel.revalidate();
  }

  @Override
  public void addButtonListener(ActionListener listener) {
    button.addActionListener(listener);
  }

  @Override
  public void setButtonEnabled(boolean enabled) {
    button.setEnabled(enabled);
  }

  @Override
  public void delete() {
    contentPanel.remove(label);
    contentPanel.remove(button);
    contentPanel.revalidate();
  }
}
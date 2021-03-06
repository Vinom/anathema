package net.sf.anathema.framework;

import net.sf.anathema.framework.repository.IObjectSelectionProperties;
import net.sf.anathema.lib.gui.dialog.userdialog.page.AbstractDialogPage;
import net.sf.anathema.lib.message.IBasicMessage;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.BorderLayout;

public class ObjectSelectionDialogPage extends AbstractDialogPage {

  private final JPanel content = new JPanel(new BorderLayout());
  private final JComboBox comboBox;
  private final IObjectSelectionProperties properties;

  public ObjectSelectionDialogPage(Object[] objects, IObjectSelectionProperties properties) {
    super(properties.getDefaultMessage().getText());
    this.properties = properties;
    this.comboBox = new JComboBox(objects);
  }

  @Override
  public IBasicMessage createCurrentMessage() {
    return getDefaultMessage();
  }

  @Override
  public JComponent createContent() {
    content.add(comboBox, BorderLayout.NORTH);
    return content;
  }

  @Override
  public String getTitle() {
    return properties.getTitle();
  }

  public Object getSelectedObject() {
    return comboBox.getSelectedItem();
  }
}
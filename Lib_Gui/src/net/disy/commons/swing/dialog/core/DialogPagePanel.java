/**
 * Copyright (C) 2005, 2011 disy Informationssysteme GmbH and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */
package net.disy.commons.swing.dialog.core;

import net.disy.commons.core.message.IBasicMessage;
import net.disy.commons.core.model.ObjectModel;
import net.disy.commons.core.util.Ensure;
import net.disy.commons.swing.dialog.core.message.DialogMessageModel;
import net.disy.commons.swing.dialog.userdialog.IMessageSetable;
import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.border.Border;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;

public class DialogPagePanel implements IMessageSetable {

  private static final Border DEFAULT_BORDER = BorderFactory.createEmptyBorder(10, 8, 10, 8);
  private static final Border WITH_TOOLBAR_BORDER = BorderFactory.createEmptyBorder(0, 8, 10, 8);
  private final JPanel contentPanel;
  private final IDialogHeaderPanelConfiguration headerPanelConfiguration;
  private final DialogMessageModel messageModel = new DialogMessageModel();
  private final ObjectModel<String> descriptionModel = new ObjectModel<String>();
  private JComponent content;

  public DialogPagePanel(final IDialogHeaderPanelConfiguration headerPanelConfiguration) {
    Ensure.ensureArgumentNotNull(headerPanelConfiguration);
    this.headerPanelConfiguration = headerPanelConfiguration;
    contentPanel = new JPanel(new GridLayout(0, 1));
    contentPanel.setBorder(DEFAULT_BORDER);
  }

  public JComponent createPanel() {
    final JPanel dialogPagePanel = new JPanel(new BorderLayout());
    if (headerPanelConfiguration.isHeaderPanelVisible()) {
      final DialogHeaderPanel headerPanel = new DialogHeaderPanel(
          messageModel,
          descriptionModel,
          headerPanelConfiguration.getLargeDialogIcon());
      dialogPagePanel.add(headerPanel.getContent(), BorderLayout.NORTH);
    }
    dialogPagePanel.add(createDialogPagePanel(), BorderLayout.CENTER);
    return dialogPagePanel;
  }

  private Component createDialogPagePanel() {
    final JPanel dialogPagePanel = new JPanel(new GridDialogLayout(1, false, 0, 0));
    final GridDialogLayoutData data = new GridDialogLayoutData(GridDialogLayoutData.FILL_BOTH);
    data.setWidthHint(IDialogConstants.MINIMUM_CONTENT_SIZE.width);
    data.setHeightHint(IDialogConstants.MINIMUM_CONTENT_SIZE.height);
    if (headerPanelConfiguration.getToolBar() != null) {
      contentPanel.setBorder(WITH_TOOLBAR_BORDER);
      dialogPagePanel.add(
          headerPanelConfiguration.getToolBar(),
          GridDialogLayoutData.FILL_HORIZONTAL);
    }
    dialogPagePanel.add(contentPanel, data);
    return dialogPagePanel;
  }

  @Override
  public final void setMessage(final IBasicMessage message) {
    messageModel.setMessage(message);
    contentPanel.validate();
    contentPanel.repaint();
  }

  public final void setDescription(final String description) {
    descriptionModel.setValue(description);
  }

  public void setContent(final JComponent content) {
    this.content = content;
    contentPanel.removeAll();
    contentPanel.add(content);
    contentPanel.revalidate();
  }

  public JComponent getContent() {
    return content;
  }

  public Dimension getPreferredSize() {
    return contentPanel.getPreferredSize();
  }

  public Dimension getSize() {
    return contentPanel.getSize();
  }

}
/*
 * The contents of this file are subject to the terms
 * of the Common Development and Distribution License
 * (the License).  You may not use this file except in
 * compliance with the License.
 * 
 * You can obtain a copy of the license at
 * https://woodstock.dev.java.net/public/CDDLv1.0.html.
 * See the License for the specific language governing
 * permissions and limitations under the License.
 * 
 * When distributing Covered Code, include this CDDL
 * Header Notice in each file and include the License file
 * at https://woodstock.dev.java.net/public/CDDLv1.0.html.
 * If applicable, add the following below the CDDL Header,
 * with the fields enclosed by brackets [] replaced by
 * you own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 * 
 * Copyright 2007 Sun Microsystems, Inc. All rights reserved.
 */
package com.sun.webui.jsf.component.vforms;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import javax.faces.component.ActionSource;
import javax.faces.component.EditableValueHolder;
import javax.swing.Icon;
import javax.swing.JTable;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import com.sun.webui.jsf.component.Form;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import com.sun.rave.designtime.DesignBean;
import com.sun.rave.designtime.DesignContext;
import com.sun.rave.designtime.DesignProperty;
import com.sun.rave.designtime.Result;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.swing.table.TableCellEditor;
import javax.faces.component.UIComponent;
import javax.faces.component.NamingContainer;
import com.sun.webui.jsf.component.FormDesignInfo;
import com.sun.rave.designtime.ext.componentgroup.ComponentGroupHolder;
import com.sun.rave.designtime.ext.componentgroup.impl.ColorWrapperImpl;
import com.sun.rave.designtime.ext.componentgroup.util.ComponentGroupHelper;


/**
 * @author mbohm
 * @author  jhoff
 */
public class EditVirtualFormsCustomizerPanel
        extends javax.swing.JPanel {
    
    public Result applyChanges() {
        // store the virtual forms config
        Form.VirtualFormDescriptor[] vforms = (Form.VirtualFormDescriptor[])
        vformsList.toArray(new Form.VirtualFormDescriptor[vformsList.size()]);
        String vfConfig = Form.generateVirtualFormsConfig(vforms);
        DesignProperty vfcProp = formBean.getProperty("virtualFormsConfig"); // NOI18N
        vfcProp.setValue(vfConfig);
        
        // store off the form colors
        DesignContext context = customizer.getDesignBean().getDesignContext();
        for (int i = 0; vforms != null && i < vforms.length; i++) {
            String vfName = vforms[i].getName();
            String key = getColorKey(vfName);
            Color c = (Color)colorMap.get(key);
            if (c != null) {
                context.setContextData(key, new ColorWrapperImpl(c));
            }
        }
        
        // reset the customizer modified state
        customizer.setModified(false);
        return null;
    }
    
    protected EditVirtualFormsCustomizer customizer;
    protected DesignBean[] beans;
    public EditVirtualFormsCustomizerPanel(EditVirtualFormsCustomizer customizer) {
        this.customizer = customizer;
        this.beans = customizer.getDesignBeans();
        this.canParticipate = initCanParticipate();
        this.canSubmit = initCanSubmit();
        initComponents();
        readVFormInfo();
    }
    
    /** Creates new form EditVirtualFormDialogBox */
    public EditVirtualFormsCustomizerPanel() {
        initComponents();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        tableLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        vformsTable = new javax.swing.JTable();

        // Only one row selectable at a time
        vformsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Create a default cell editor for String values that forces a stop
        // editing event whenever focus is lost.
        JTextField textField = new JTextField();
        final TableCellEditor cellEditor = new TextFieldCellEditor(vformsTable, textField);

        vformsTable.setDefaultEditor(String.class, cellEditor);

        // Single click to start editing cells with String
        ((DefaultCellEditor)vformsTable.getDefaultEditor(String.class)).setClickCountToStart( 1 );

        // Create a default cell renderer for String values that consistently renders
        // background colors.
        vformsTable.setDefaultRenderer(String.class, new HomogonousCellRenderer());

        // Stop the editing when the table lost its focus
        vformsTable.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);

        btnNew = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();

        setLayout(new java.awt.GridBagLayout());

        setPreferredSize(new java.awt.Dimension(550, 250));
        getAccessibleContext().setAccessibleName("Edit Virtual Forms dialog box");
        getAccessibleContext().setAccessibleDescription("Use this table to view and edit properties of the virutal forms defined on this page.");
        tableLabel.setDisplayedMnemonic(java.util.ResourceBundle.getBundle("com/sun/webui/jsf/component/vforms/Bundle").getString("labelMnemonic").charAt(0));
        tableLabel.setLabelFor(vformsTable);
        tableLabel.setText(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("com/sun/webui/jsf/component/vforms/Bundle").getString("editLabel"), new Object[] {this.getSelectedComponentsDisplayText()}));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 5, 10);
        add(tableLabel, gridBagConstraints);
        tableLabel.getAccessibleContext().setAccessibleName(java.util.ResourceBundle.getBundle("com/sun/webui/jsf/component/vforms/Bundle").getString("tableLabelAccessibleName"));
        tableLabel.getAccessibleContext().setAccessibleDescription(java.util.ResourceBundle.getBundle("com/sun/webui/jsf/component/vforms/Bundle").getString("editTableLabelAccessibleDescription"));

        jScrollPane1.setBackground(java.awt.SystemColor.window);
        jScrollPane1.setViewportView(vformsTable);
        vformsTable.getAccessibleContext().setAccessibleName(java.util.ResourceBundle.getBundle("com/sun/webui/jsf/component/vforms/Bundle").getString("vformsTableAccessibleName"));
        vformsTable.getAccessibleContext().setAccessibleDescription(java.util.ResourceBundle.getBundle("com/sun/webui/jsf/component/vforms/Bundle").getString("editVformsTableAccessibleDescription"));

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 5, 5);
        add(jScrollPane1, gridBagConstraints);
        jScrollPane1.getAccessibleContext().setAccessibleName(java.util.ResourceBundle.getBundle("com/sun/webui/jsf/component/vforms/Bundle").getString("vformsTableAccessibleName"));
        jScrollPane1.getAccessibleContext().setAccessibleDescription(java.util.ResourceBundle.getBundle("com/sun/webui/jsf/component/vforms/Bundle").getString("editVformsTableAccessibleDescription"));

        btnNew.setMnemonic(java.util.ResourceBundle.getBundle("com/sun/webui/jsf/component/vforms/Bundle").getString("newButtonMnemonic").charAt(0));
        btnNew.setText(java.util.ResourceBundle.getBundle("com/sun/webui/jsf/component/vforms/Bundle").getString("New"));
        btnNew.setToolTipText(java.util.ResourceBundle.getBundle("com/sun/webui/jsf/component/vforms/Bundle").getString("NewVf"));
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 10);
        add(btnNew, gridBagConstraints);
        btnNew.getAccessibleContext().setAccessibleName(java.util.ResourceBundle.getBundle("com/sun/webui/jsf/component/vforms/Bundle").getString("btnNewAccessibleName"));
        btnNew.getAccessibleContext().setAccessibleDescription(java.util.ResourceBundle.getBundle("com/sun/webui/jsf/component/vforms/Bundle").getString("btnNewAccessibleDescription"));

        btnDelete.setMnemonic(java.util.ResourceBundle.getBundle("com/sun/webui/jsf/component/vforms/Bundle").getString("deleteButtonMnemonic").charAt(0));
        btnDelete.setText(java.util.ResourceBundle.getBundle("com/sun/webui/jsf/component/vforms/Bundle").getString("Delete"));
        btnDelete.setToolTipText(java.util.ResourceBundle.getBundle("com/sun/webui/jsf/component/vforms/Bundle").getString("DeleteVf"));
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        add(btnDelete, gridBagConstraints);
        btnDelete.getAccessibleContext().setAccessibleName(java.util.ResourceBundle.getBundle("com/sun/webui/jsf/component/vforms/Bundle").getString("btnDeleteAccessibleName"));
        btnDelete.getAccessibleContext().setAccessibleDescription(java.util.ResourceBundle.getBundle("com/sun/webui/jsf/component/vforms/Bundle").getString("btnDeleteAccessibleDescription"));

    }
    // </editor-fold>//GEN-END:initComponents
    
    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        int vfidx = vformsTable.getSelectedRow();
        if (vfidx > -1 && vfidx < vformsList.size()) {
            Form.VirtualFormDescriptor vform = (Form.VirtualFormDescriptor)vformsList.get(vfidx);
            String title = ResourceBundle.getBundle("com/sun/webui/jsf/component/vforms/Bundle").getString("confirmDeleteTitle"); // NOI18N
            String msg = java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("com/sun/webui/jsf/component/vforms/Bundle").getString("confirmDeleteMessage"), new Object[] {vform.getName()});  // NOI18N
            if (JOptionPane.showConfirmDialog(this, msg, title, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                vformsList.remove(vfidx);
                vformsTableModel.fireTableDataChanged();
                if (vformsList.size() <= vfidx) {
                    vfidx--;
                }
                if (vfidx >= 0) {
                    vformsTable.getSelectionModel().setSelectionInterval(vfidx, vfidx);
                }
            }
        }
    }//GEN-LAST:event_btnDeleteActionPerformed
    
    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        String name = VirtualFormsHelper.getNewVirtualFormName(vformsList);
        
        Form.VirtualFormDescriptor vform = new Form.VirtualFormDescriptor(name);
        vformsList.add(vform);
        
        vformsTableModel.fireTableDataChanged();
        vformsTable.getSelectionModel().setSelectionInterval(vformsList.size() - 1, vformsList.size() - 1);
    }//GEN-LAST:event_btnNewActionPerformed
        
    class FormsTableModel extends AbstractTableModel {
        public FormsTableModel() {}
        public int getRowCount() {
            return vformsList.size();
        }
        public int getColumnCount() {
            return 4;
        }
        public String getColumnName(int columnIndex) {
            switch(columnIndex) {
                case 0: // color
                    return ResourceBundle.getBundle("com/sun/webui/jsf/component/vforms/Bundle").getString("colorHeader"); // NOI18N
                case 1: // virtual form name
                    return ResourceBundle.getBundle("com/sun/webui/jsf/component/vforms/Bundle").getString("nameHeader"); // NOI18N
                case 2: // participates
                    return ResourceBundle.getBundle("com/sun/webui/jsf/component/vforms/Bundle").getString("participateHeader"); // NOI18N
                case 3: // submits
                    return ResourceBundle.getBundle("com/sun/webui/jsf/component/vforms/Bundle").getString("submitHeader"); // NOI18N
            }
            return null;
        }
        public Class getColumnClass(int columnIndex) {
            switch(columnIndex) {
                case 0: // color
                    return Color.class;
                case 1: // virtual form name
                    return String.class;
                case 2: // participates: boolean
                    return String.class;
                case 3: // submits: boolean
                    return String.class;
            }
            return null;
        }
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            Form.VirtualFormDescriptor vform = (Form.VirtualFormDescriptor)vformsList.get(rowIndex);
            if (vform != null) {
                switch (columnIndex) {
                    case 0: // virtual form color
                        return true;
                    case 1: // virtual form name
                        return true;
                    case 2: // participates
                        return EditVirtualFormsCustomizerPanel.this.canParticipate;
                    case 3: // submits
                        return EditVirtualFormsCustomizerPanel.this.canSubmit;
                }
            }
            return false;
        }
        public Object getValueAt(int rowIndex, int columnIndex) {
            Form.VirtualFormDescriptor vform = (Form.VirtualFormDescriptor)vformsList.get(rowIndex);
            if (vform != null) {
                switch (columnIndex) {
                    case 0: // virtual form color
                        String vfName = vform.getName();
                        String key = getColorKey(vfName);
                        return ComponentGroupHelper.getMappedColor(key, colorMap);
                    case 1: // virtual form name
                        return vform.getName();
                    case 2: // participates
                        return getParticipates(vform);
                    case 3: // submits
                        return getSubmits(vform);
                        
                }
            }
            return null;
        }
        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
            Form.VirtualFormDescriptor vform = (Form.VirtualFormDescriptor)vformsList.get(rowIndex);
            if (vform != null) {
                String vfName, colorKey;
                switch (columnIndex) {
                    case 0: //color
                        vfName = vform.getName();
                        colorKey = getColorKey(vfName);
                        colorMap.put(colorKey, aValue);
                        customizer.setModified(true);
                        return;
                    case 1: // virtual form name/color
                        String name = aValue.toString();
                        name = name.trim();
                        name = name.replaceAll("\\|", "_"); // NOI18N
                        name = name.replaceAll(",", "_"); // NOI18N
                        if (name.length() < 1) {
                            name = VirtualFormsHelper.getNewVirtualFormName(vformsList);
                        }
                        vfName = vform.getName();
                        colorKey = getColorKey(vfName);
                        Color c = (Color)colorMap.get(colorKey);
                        colorMap.remove(colorKey);
                        vform.setName(name);
                        colorKey = getColorKey(name);
                        colorMap.put(colorKey, c);
                        customizer.setModified(true);
                        return;
                    case 2: // participates
                        String pvalue = (String)aValue;
                        boolean participates = YES.equals(pvalue);
                        setParticipates(vform, participates);
                        return;
                    case 3: // submits
                        String svalue = (String)aValue;
                        boolean submits = YES.equals(svalue);
                        setSubmits(vform, submits);
                        if (submits) {
                            for (Iterator iter = vformsList.iterator(); iter.hasNext(); ) {
                                Form.VirtualFormDescriptor vf = (Form.VirtualFormDescriptor)iter.next();
                                if (!vf.getName().equals(vform.getName())) {    //vf is different from the virtual form which the components now submit
                                    setSubmits(vf, false);
                                }
                            }
                            fireTableDataChanged();
                        }
                        return;
                }
            }
        }
    }
    
    private boolean initCanParticipate() {
        for (int i = 0; i < beans.length; i++) {
            if (beans[i].getInstance() instanceof EditableValueHolder) {
                return true;
            }
        }
        return false;
    }

    private String getParticipates(Form.VirtualFormDescriptor vform) {
        boolean atLeastOneParticipates = false;
        boolean atLeastOneDoesNot = false;
        for (int i = 0; i < beans.length; i++) {
            Object beanInstance = beans[i].getInstance();
            boolean beanCouldParticipate = beanInstance instanceof EditableValueHolder;
            if (beanCouldParticipate) {
                String beanFqId = FormDesignInfo.getFullyQualifiedId(beans[i]);
                if (beanFqId == null) {
                    continue;
                }
                if (vform.hasParticipant(beanFqId)) {
                    atLeastOneParticipates = true;
                    if (atLeastOneDoesNot) {
                        return SOME_PARTICIPATE;
                    }
                }
                else {
                    atLeastOneDoesNot = true;
                    if (atLeastOneParticipates) {
                        return SOME_PARTICIPATE;
                    }
                }
            }
        }
        if (atLeastOneParticipates) {
            return YES;
        }
        else {
            return NO;
        }
    }

    private void setParticipates(Form.VirtualFormDescriptor vform, boolean participate) {
        String[] pids = vform.getParticipatingIds();
        ArrayList pList = new ArrayList();
        for (int i = 0; pids != null && i < pids.length; i++) {
            pList.add(pids[i]);
        }
        for (int i = 0; i < beans.length; i++) {
            Object beanInstance = beans[i].getInstance();
            if (! (beanInstance instanceof UIComponent)) {
                continue;
            }
            String fqId = FormDesignInfo.getFullyQualifiedId(beans[i]);
            if (fqId == null) {
                continue;
            }
            List relevantPids = new ArrayList();
            for (Iterator iter = pList.iterator(); iter.hasNext(); ) {
                String pid = (String)iter.next();
                if (Form.fullyQualifiedIdMatchesPattern(fqId, pid)) {
                    relevantPids.add(pid);
                }
            }
            boolean contains = relevantPids.size() > 0;
            if (participate && !contains && beanInstance instanceof EditableValueHolder) {
                String idToAdd = fqId;
                if (idToAdd.startsWith(String.valueOf(NamingContainer.SEPARATOR_CHAR)) && idToAdd.length() > 1) {
                    idToAdd = idToAdd.substring(1, idToAdd.length());
                }
                pList.add(idToAdd);
                customizer.setModified(true);
            } else if (!participate && contains) {
                for (Iterator iter = relevantPids.iterator(); iter.hasNext(); ) {
                    Object relevantPid = iter.next();
                    pList.remove(relevantPid);
                    customizer.setModified(true);
                }
            }
        }
        pids = (String[])pList.toArray(new String[pList.size()]);
        vform.setParticipatingIds(pids);
    }
    
    private boolean initCanSubmit() {
        for (int i = 0; i < beans.length; i++) {
            if (beans[i].getInstance() instanceof ActionSource || beans[i].getInstance() instanceof EditableValueHolder) {
                return true;
            }
        }
        return false;
    }
    private String getSubmits(Form.VirtualFormDescriptor vform) {
        boolean atLeastOneSubmits = false;
        boolean atLeastOneDoesNot = false;
        for (int i = 0; i < beans.length; i++) {
            String beanFqId = FormDesignInfo.getFullyQualifiedId(beans[i]);
            if (beanFqId == null) {
                continue;
            }
            if (vform.isSubmittedBy(beanFqId)) {
                atLeastOneSubmits = true;
                if (atLeastOneDoesNot) {
                    return SOME_SUBMIT;
                }
            }
            else {
                atLeastOneDoesNot = true;
                if (atLeastOneSubmits) {
                    return SOME_SUBMIT;
                }
            }
        }
        if (atLeastOneSubmits) {
            return YES;
        }
        else {
            return NO;
        }
    }
    private void setSubmits(Form.VirtualFormDescriptor vform, boolean submit) {
        String[] sids = vform.getSubmittingIds();
        ArrayList sList = new ArrayList();
        for (int i = 0; sids != null && i < sids.length; i++) {
            sList.add(sids[i]);
        }
        for (int i = 0; i < beans.length; i++) {
            Object beanInstance = beans[i].getInstance();
            if (! (beanInstance instanceof UIComponent)) {
                continue;
            }
            String fqId = FormDesignInfo.getFullyQualifiedId(beans[i]);
            if (fqId == null) {
                continue;
            }
            List relevantSids = new ArrayList();
            for (Iterator iter = sList.iterator(); iter.hasNext(); ) {
                String sid = (String)iter.next();
                if (Form.fullyQualifiedIdMatchesPattern(fqId, sid)) {
                    relevantSids.add(sid);
                }
            }
            boolean contains = relevantSids.size() > 0;
            if (submit && !contains && (beanInstance instanceof ActionSource || beanInstance instanceof EditableValueHolder)) {
                String idToAdd = fqId;
                if (idToAdd.startsWith(String.valueOf(NamingContainer.SEPARATOR_CHAR)) && idToAdd.length() > 1) {
                    idToAdd = idToAdd.substring(1, idToAdd.length());
                }
                sList.add(idToAdd);
                customizer.setModified(true);
            } else if (!submit && contains) {
                for (Iterator iter = relevantSids.iterator(); iter.hasNext(); ) {
                    Object relevantSid = iter.next();
                    sList.remove(relevantSid);
                    customizer.setModified(true);
                }
            }
        }
        sids = (String[])sList.toArray(new String[sList.size()]);
        vform.setSubmittingIds(sids);
    }
    
    private DesignBean formBean;
    private List inputBeans = new ArrayList();
    private List actionBeans = new ArrayList();
    private List vformsList = new ArrayList();
    private Map colorMap = new HashMap();
    private FormsTableModel vformsTableModel = new FormsTableModel();
    private boolean canParticipate = true;
    private boolean canSubmit = true;
    private static final String YES = ResourceBundle.getBundle("com/sun/webui/jsf/component/vforms/Bundle").getString("yes"); // NOI18N
    private static final String NO = ResourceBundle.getBundle("com/sun/webui/jsf/component/vforms/Bundle").getString("no"); // NOI18N
    private static final String SOME_PARTICIPATE = ResourceBundle.getBundle("com/sun/webui/jsf/component/vforms/Bundle").getString("someParticipate"); // NOI18N
    private static final String SOME_SUBMIT = ResourceBundle.getBundle("com/sun/webui/jsf/component/vforms/Bundle").getString("someSubmit"); // NOI18N
    private static final String[] NO_ONLY = new String[]{NO};
    private static final String[] YES_NO = new String[]{YES, NO};
    private static final String[] YES_NO_SOME_PARTICIPATE = new String[]{YES, NO, SOME_PARTICIPATE};
    private static final String[] YES_NO_SOME_SUBMIT = new String[]{YES, NO, SOME_SUBMIT};
    
    private void readVFormInfo() {
        formBean = VirtualFormsHelper.findFormBean(beans);
        DesignContext dcontext = formBean.getDesignContext();
        ComponentGroupHolder[] holders = ComponentGroupHelper.getComponentGroupHolders(dcontext);
        ComponentGroupHelper.populateColorMap(dcontext, holders, colorMap);
        Form form = (Form)formBean.getInstance();
        Form.VirtualFormDescriptor[] vforms = form.getVirtualForms();
        for (int i = 0; vforms != null && i < vforms.length; i++) {
            String vformName = vforms[i].getName();
            Form.VirtualFormDescriptor vformCopy = new Form.VirtualFormDescriptor(vformName);
            //it so happens we never modify the individual members of the participating or submitting arrays
            //i.e., in setParticipates and setSubmits we use vform.setParticipatingIds(pids) and vform.setSubmittingIds(sids)
            //so a deep copy of the individual members of these arrays is not necessary
            vformCopy.setParticipatingIds(vforms[i].getParticipatingIds());
            vformCopy.setSubmittingIds(vforms[i].getSubmittingIds());
            vformsList.add(vformCopy);
        }
        
        vformsTable.setModel(vformsTableModel);
        vformsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        TableColumn colorCol = vformsTable.getColumnModel().getColumn(0);
        colorCol.setCellRenderer(new ColorCellRenderer());
        colorCol.setCellEditor(new DefaultCellEditor(new ColorComboBox()));
        // Takes two click to have the list popup
        ((DefaultCellEditor)colorCol.getCellEditor()).setClickCountToStart(2);
        
        TableColumn psCol = vformsTable.getColumnModel().getColumn(2);
        psCol.setCellEditor(new DefaultCellEditor(new PSComboBox(true)));
        // Takes two click to have the list popup
        ((DefaultCellEditor)psCol.getCellEditor()).setClickCountToStart(2);
        
        psCol = vformsTable.getColumnModel().getColumn(3);
        psCol.setCellEditor(new DefaultCellEditor(new PSComboBox(false)));
        // Takes two click to have the list popup
        ((DefaultCellEditor)psCol.getCellEditor()).setClickCountToStart(2);
        
        // Have the first row selected by default
        if( vformsTableModel.getRowCount() > 0 ) 
            vformsTable.changeSelection( 0, 0, false, false );
    }
    
    private String[] getOptions(boolean participate) {
        if (participate) {
            boolean atLeastOneEvh = false;
            for (int i = 0; i < beans.length; i++) {
                Object instance = beans[i].getInstance();
                if (instance instanceof EditableValueHolder) {
                    atLeastOneEvh = true;
                    break;
                }
            }
            if (!atLeastOneEvh) {
                return NO_ONLY;
            }
         }
        return YES_NO;
    }
    
    private String getSelectedComponentsDisplayText() {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; this.beans != null && i < this.beans.length; i++) {
            sb.append(beans[i].getInstanceName());
            sb.append("<br>");  //NOI18N;
        }
        return sb.toString();
    }
    
    private String getColorKey(String vfName) {
        String formBeanId = formBean.getInstanceName();
        String formIdDotVfName = formBeanId + "." + vfName;
        return ComponentGroupHelper.getComponentGroupColorKey(FormDesignInfo.VIRTUAL_FORM_HOLDER_NAME, formIdDotVfName);   
    }
    
    class ColorCellRenderer extends DefaultTableCellRenderer {
        
        Color SELECTION_BACKGROUND =
            UIManager.getDefaults().getColor("TextField.selectionBackground");
    
        Color SELECTION_FOREGROUND =
            UIManager.getDefaults().getColor("TextField.selectionForeground");
    
        Color BACKGROUND =
            UIManager.getDefaults().getColor("TextField.background");
    
        Color FOREGROUND =
            UIManager.getDefaults().getColor("TextField.foreground");
        
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            
            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            
            if (isSelected) {
                c.setBackground(SELECTION_BACKGROUND);
                c.setForeground(SELECTION_FOREGROUND);
            }
            else {
                c.setBackground(BACKGROUND);
                c.setForeground(FOREGROUND);
            }
            
            Color color = (Color)value;
            setIcon(new ColorIcon(color));
            setText(null);
            
            return this;
        }
    }

    class ColorIcon implements Icon {
        private Color color;
        public ColorIcon(Color color) {
            this.color = color;
        }
        public void paintIcon(Component c, Graphics g, int x, int y) {
            g.setColor(color);
            g.fillRect(x, y, 8, 8);
            g.setColor(Color.black);
            g.drawRect(x, y, 8, 8);
        }
        public int getIconWidth() {
            return 8;
        }
        public int getIconHeight() {
            return 8;
        }
    }
    
    class ColorComboBox extends JComboBox {
        public ColorComboBox() {
            super();
            DefaultComboBoxModel cbm = new DefaultComboBoxModel();
            for (int i = 0; i < ComponentGroupHelper.DEFAULT_COLOR_SET.length; i++) {
                Color c = ComponentGroupHelper.DEFAULT_COLOR_SET[i];
                cbm.addElement(c);
            }
            setModel(cbm);
            setRenderer(new ColorListRenderer());
            getAccessibleContext().setAccessibleName(ResourceBundle.getBundle("com/sun/webui/jsf/component/vforms/Bundle").getString("colorComboAccessibleName")); // NOI18N
            getAccessibleContext().setAccessibleDescription(ResourceBundle.getBundle("com/sun/webui/jsf/component/vforms/Bundle").getString("colorComboAccessibleDescription")); // NOI18N
        }
    }
    
    class PSComboBox extends JComboBox {
        public PSComboBox(boolean participates) {
            super();
            setOptions(getOptions(participates));
        }
        
        private void setOptions(String[] options) {
            DefaultComboBoxModel cbm = new DefaultComboBoxModel();
            for (int i = 0; i < options.length; i++) {
                cbm.addElement(options[i]);
            }
            setModel(cbm);
        }
    }
    
    class ColorListRenderer extends DefaultListCellRenderer {
        public Component getListCellRendererComponent(JList list, Object value,
                int index, boolean isSelected, boolean hasFocus) {
            
            super.getListCellRendererComponent(list, value, index, isSelected, hasFocus);
            
            Color c = (Color)value;
            setIcon(new ColorIcon(c));
            setText(null);
            
            return this;
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnNew;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel tableLabel;
    private javax.swing.JTable vformsTable;
    // End of variables declaration//GEN-END:variables

    
}

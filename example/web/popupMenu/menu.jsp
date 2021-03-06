<jsp:root version="2.0" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:webuijsf="http://www.sun.com/webui/webuijsf">
 <jsp:directive.page contentType="text/html"/>
 <f:view>    
    <!--
      The contents of this file are subject to the terms
      of the Common Development and Distribution License
      (the License).  You may not use this file except in
      compliance with the License.
      
      You can obtain a copy of the license at
      https://woodstock.dev.java.net/public/CDDLv1.0.html.
      See the License for the specific language governing
      permissions and limitations under the License.
      
      When distributing Covered Code, include this CDDL
      Header Notice in each file and include the License file
      at https://woodstock.dev.java.net/public/CDDLv1.0.html.
      If applicable, add the following below the CDDL Header,
      with the fields enclosed by brackets [] replaced by
      you own identifying information:
      "Portions Copyrighted [year] [name of copyright owner]"
      
      Copyright 2007 Sun Microsystems, Inc. All rights reserved.
    -->
  <webuijsf:page>
      <f:loadBundle basename="com.sun.webui.jsf.example.resources.Resources" var="msgs" />
            <webuijsf:html id="html">  
                <webuijsf:head id="menuHead" title="#{msgs.popupMenu_title}">
                    <webuijsf:link rel="shortcut icon" url="/images/favicon.ico" type="image/x-icon" />
                    <webuijsf:script type="text/javascript" url="menu.js"/>                    
                </webuijsf:head>
                <webuijsf:body>
                    <webuijsf:form id="form1">
                
                        <!-- Masthead -->
                        <webuijsf:masthead id="Masthead" productImageURL="/images/example_primary_masthead.png"
                           productImageDescription="#{msgs.mastheadAltText}" 
                           userInfo="test_user"
                           serverInfo="test_server" />  
                         
                       <!-- Bread Crumb Component -->
                        <webuijsf:breadcrumbs id="breadcrumbs">
                            <webuijsf:hyperlink id="exampleLink"
                                text="#{msgs.index_title}"
                                toolTip="#{msgs.index_title}"
                                actionExpression="#{popupBean.showExampleIndex}" 
                                onMouseOver="javascript:window.status='#{msgs.index_breadcrumbMouseOver}'; return true;"
                                onMouseOut="javascript: window.status=''; return true" />         
                              <webuijsf:hyperlink id="menuLink" text="#{msgs.popupMenu_index}"/>
                          </webuijsf:breadcrumbs>   
                          <webuijsf:markup tag="div" styleClass="#{themeStyles.CONTENT_MARGIN}">                            
                             <br/>       <br/>      
                             
                             <!--Label for the first menu and text field -->
                              <webuijsf:label id="lb1" text="#{msgs.popupMenu_text1}"/>
                              <br/><br/>
                              
                              <!--Text field for showing the selected menu option -->
                              <webuijsf:textField id="verifier"
                                  labelLevel="2" label="#{msgs.popupMenu_label2}"
                                  text="#{popupBean.selectedItem}" disabled="true"/>                                                                                                                      
                              <br/><br/>
                              
                              <webuijsf:staticText id="static1" text="#{msgs.popupMenu_label1}"/>                       
                              <br/><br/>

                              <!-- Hyperlink used for opening the first menu -->
                              <webuijsf:hyperlink url="#" tabIndex="1" id="link1" 
                                  text="#{msgs.popupMenu_link1}" 
                                  onClick="displayMenuOne(event);return false;" 
                                  onKeyDown="displayMenuOne(event);"/>                            
                              <br/><br/>
                             
                              <!-- Label for the second menu-->
                              <webuijsf:label id="lb2" text="#{msgs.popupMenu_text2}" 
                                  style="position:absolute;top:426px;left:10px"/>                      

                              <webuijsf:staticText id="static2" text="#{msgs.popupMenu_label3}" 
                                  style="position:absolute;top:450px;left:10px"/>                                                                           

                              <!-- Hyperlink used for opening the second menu -->
                              <webuijsf:hyperlink url="#" style="position:absolute;top:480px;left:10px" 
                                  tabIndex="1" id="link2" text="#{msgs.popupMenu_link1}" 
                                  onClick="displayMenuTwo(event);return false;" 
                                  onKeyDown="displayMenuTwo(event);"/>                                                                     

                              <!-- Menu which is used for asynchronous submits -->
                              <webuijsf:menu id="popup1"  items="#{popupBean.optionItems}" 
                                  eventListenerExpression="#{popupBean.selectOption}"  
                                  onChange="setTimeout('changeEvent();', 0);"/>            `                            

                              <!-- Menu which submits the form. -->                            
                              <webuijsf:menu id="popup2"  eventExpression="#{popupBean.selectedAction}"
                                  eventListenerExpression="#{popupBean.menuSelection}" 
                                  items="#{popupBean.optionItems2}" submitForm="true"/>                        
                          </webuijsf:markup>
                    </webuijsf:form>
                </webuijsf:body> 
            </webuijsf:html>  
        </webuijsf:page>
    </f:view>
</jsp:root>

                       
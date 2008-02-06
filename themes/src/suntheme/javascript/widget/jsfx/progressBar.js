// widget/jsfx/progressBar.js
//
// The contents of this file are subject to the terms
// of the Common Development and Distribution License
// (the License).  You may not use this file except in
// compliance with the License.
// 
// You can obtain a copy of the license at
// https://woodstock.dev.java.net/public/CDDLv1.0.html.
// See the License for the specific language governing
// permissions and limitations under the License.
// 
// When distributing Covered Code, include this CDDL
// Header Notice in each file and include the License file
// at https://woodstock.dev.java.net/public/CDDLv1.0.html.
// If applicable, add the following below the CDDL Header,
// with the fields enclosed by brackets [] replaced by
// you own identifying information:
// "Portions Copyrighted [year] [name of copyright owner]"
// 
// Copyright 2007 Sun Microsystems, Inc. All rights reserved.
//

webui.@THEME@.dojo.provide("webui.@THEME@.widget.jsfx.progressBar");

webui.@THEME@.dojo.require("webui.@THEME@.json");
webui.@THEME@.dojo.require("webui.@THEME@.widget.jsfx.common");
webui.@THEME@.dojo.require("webui.@THEME@.widget.jsfx.dynaFaces");
webui.@THEME@.dojo.require("webui.@THEME@.widget.progressBar");

/**
 * @class This class contains functions to obtain data asynchronously using JSF
 * Extensions as the underlying transfer protocol.
 * @static
 */
webui.@THEME@.widget.jsfx.progressBar =  {
    /**
     * This function is used to process progress events with Object literals.
     *
     * @param props Key-Value pairs of properties.
     * @config {String} id The HTML element Id.
     * @return {boolean} true if successful; otherwise, false.
     */
    processProgressEvent: function(props) {
        if (props == null) {
            return false;
        }

        // Dynamic Faces requires a DOM node as the source property.
        var domNode = document.getElementById(props.id);

        // Generate AJAX request using the JSF Extensions library.
        DynaFaces.fireAjaxTransaction(
            (domNode) ? domNode : document.forms[0], {
            execute: props.id, // Need to decode hidden field.
            render: props.id,
            replaceElement: webui.@THEME@.widget.jsfx.progressBar.progressCallback,
            xjson: {
                id: props.id,
                event: "progress"
            }
        });

        return true;
    },

    /**
     * This function is used to update progress.
     *
     * @param {String} elementId The HTML element Id.
     * @param {String} content The content returned by the AJAX response.
     * @param {Object} closure The closure argument provided to DynaFaces.fireAjaxTransaction.
     * @param {Object} xjson The xjson argument provided to DynaFaces.fireAjaxTransaction.
     * @return {boolean} true if successful; otherwise, false.
     */
    progressCallback: function(id, content, closure, xjson) {
        if (id == null || content == null) {
            return false;
        }

        // Parse JSON text.
        var props = webui.@THEME@.json.parse(content);

        // Set progress.
        var widget = webui.@THEME@.dijit.byId(id);
        widget.setProgress({
            failedStateText : props.failedStateText,
            logMessage : props.logMessage,
            progress : props.progress,
            status: props.status,
            taskState : props.taskState,
            topText : props.topText
        });

        // Publish an event for custom AJAX implementations to listen for.
        webui.@THEME@.dojo.publish(
            webui.@THEME@.widget.progressBar.event.progress.endTopic, [props]);
        return true;
    }
}

// Listen for Dojo Widget events.
webui.@THEME@.dojo.subscribe(webui.@THEME@.widget.progressBar.event.progress.beginTopic,
    webui.@THEME@.widget.jsfx.progressBar, "processProgressEvent");
webui.@THEME@.dojo.subscribe(webui.@THEME@.widget.progressBar.event.refresh.beginTopic,
    webui.@THEME@.widget.jsfx.common, "processRefreshEvent");

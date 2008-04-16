/**
 * The contents of this file are subject to the terms
 * of the Common Development and Distribution License
 * (the License). You may not use this file except in
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
 * Copyright 2008 Sun Microsystems, Inc. All rights reserved.
 */

webui.@THEME_JS@._dojo.provide("webui.@THEME_JS@.widget.imageButton");

webui.@THEME_JS@._dojo.require("webui.@THEME_JS@.widget.button");

/**
 * This function is used to construct a imageButton widget.
 *
 * @name webui.@THEME_JS@.widget.imageButton
 * @extends webui.@THEME_JS@.widget.button
 * @class This class contains functions for the imageButton widget.
 * @constructor
 * @param {Object} props Key-Value pairs of properties.
 * @config {String} alt Alternate text for image input.
 * @config {String} align Alignment of image input.
 * @config {String} className CSS selector.
 * @config {String} prefix The application context path of image 
 * @config {String} dir Specifies the directionality of text.
 * @config {boolean} disabled Disable element.
 * @config {String} escape HTML escape button text (default).
 * @config {String} id Uniquely identifies an element within a document.
 * @config {String} lang Specifies the language of attribute values and content.
 * @config {String} onBlur Element lost focus.
 * @config {String} onClick Mouse button is clicked on element.
 * @config {String} onDblClick Mouse button is double-clicked on element.
 * @config {String} onFocus Element received focus.
 * @config {String} onKeyDown Key is pressed down over element.
 * @config {String} onKeyPress Key is pressed and released over element.
 * @config {String} onKeyUp Key is released over element.
 * @config {String} onMouseDown Mouse button is pressed over element.
 * @config {String} onMouseOut Mouse is moved away from element.
 * @config {String} onMouseOver Mouse is moved onto element.
 * @config {String} onMouseUp Mouse button is released over element.
 * @config {String} onMouseMove Mouse is moved while over element.
 * @config {String} src Source for image.
 * @config {String} style Specify style rules inline.
 * @config {int} tabIndex Position in tabbing order.
 * @config {String} title Provides a title for element.
 * @config {String} value Value of input.
 * @config {boolean} visible Hide or show element.
 */
webui.@THEME_JS@._dojo.declare("webui.@THEME_JS@.widget.imageButton",
        webui.@THEME_JS@.widget.button, {
    // Set defaults.
    _widgetType: "imageButton"  // Required for theme properties.
});

/**
 * This object contains event topics.
 * <p>
 * Note: Event topics must be prototyped for inherited functions. However, these
 * topics must also be available statically so that developers may subscribe to
 * events.
 * </p>
 * @ignore
 */
webui.@THEME_JS@.widget.imageButton.event =
        webui.@THEME_JS@.widget.imageButton.prototype.event = {
    /**
     * This object contains refresh event topics.
     * @ignore
     */
    refresh: {
        /** Refresh event topic for custom AJAX implementations to listen for. */
        beginTopic: "webui_@THEME_JS@_widget_imageButton_event_refresh_begin",

        /** Refresh event topic for custom AJAX implementations to listen for. */
        endTopic: "webui_@THEME_JS@_widget_imageButton_event_refresh_end"
    },

    /**
     * This object contains state event topics.
     * @ignore
     */
    state: {
        /** State event topic for custom AJAX implementations to listen for. */
        beginTopic: "webui_@THEME_JS@_widget_imageButton_event_state_begin",

        /** State event topic for custom AJAX implementations to listen for. */
        endTopic: "webui_@THEME_JS@_widget_imageButton_event_state_end"
    }
};

/**
 * This function is used to obtain the outermost HTML element class name.
 * <p>
 * Note: Selectors should be concatinated in order of precedence (e.g., the 
 * user's className property is always appended last).
 * </p>
 * @return {String} The outermost HTML element class name.
 * @private
 */
webui.@THEME_JS@.widget.imageButton.prototype._getClassName = function() {
    // If it is an image button, only the BUTTON3 selectors are used.
    // Note that the "mini" and "primary" values can still be set but
    // have no effect on image buttons by policy, vs by theme.
    var key = (this.disabled == true)
	? "BUTTON3_DISABLED"
	: "BUTTON3";

    var className = this._theme.getClassName(key, "");
    return (this.className)
        ? className + " " + this.className
        : className;
};

/**
 * This function is used to obtain the outermost HTML element class name during
 * an onFocus or onMouseOver event.
 * <p>
 * Note: Selectors should be concatinated in order of precedence (e.g., the 
 * user's className property is always appended last).
 * </p>
 * @return {String} The outermost HTML element class name.
 * @private
 */
webui.@THEME_JS@.widget.imageButton.prototype._getHoverClassName = function() {
    // If it is an image button, only the BUTTON3 selectors are used.
    // Note that the "mini" and "primary" values can still be set but
    // have no effect on image buttons by policy, vs by theme.
    var key = "BUTTON3_HOVER";
    var className = this._theme.getClassName(key, "");
    return (this.className)
        ? className + " " + this.className
        : className;
};

/**
 * This function is used to get widget properties. Please see the constructor 
 * detail for a list of supported properties.
 *
 * @return {Object} Key-Value pairs of properties.
 */
webui.@THEME_JS@.widget.imageButton.prototype.getProps = function() {
    var props = this._inherited("getProps", arguments);

    // Set properties.
    if (this.src) { props.src = this.src; }

    return props;
};

/**
 * This function is used to set widget properties. Please see the constructor 
 * detail for a list of supported properties.
 * <p>
 * Note: This function should only be invoked through setProps().
 * </p>
 * @param {Object} props Key-Value pairs of properties.
 * @return {boolean} true if successful; otherwise, false.
 * @private
 */
webui.@THEME_JS@.widget.imageButton.prototype._setProps = function(props) {
    if (props == null) {
        return false;
    }

    // Set properties.
    if (props.src) {
        // If context path is provided, then check whether the image has
        // context path already appended and if not, append it.
        if (this.prefix) {
            props.src = 
                webui.@THEME_JS@.widget.common._appendPrefix(this.prefix, props.src);                
        }
        this._domNode.src = props.src; 
    }

    // Set remaining properties.
    return this._inherited("_setProps", arguments);
};

/*!
 * B-JUI  v1.2 (http://b-jui.com)
 * Git@OSC (http://git.oschina.net/xknaan/B-JUI)
 * Copyright 2014 K'naan (xknaan@163.com).
 * Licensed under Apache (http://www.apache.org/licenses/LICENSE-2.0)
 */

/* ========================================================================
 * B-JUI: bjui-basedrag.js  v1.2
 * @author K'naan (xknaan@163.com)
 * -- Modified from dwz.drag.js (author:Roger Wu)
 * http://git.oschina.net/xknaan/B-JUI/blob/master/BJUI/js/bjui-basedrag.js
 * ========================================================================
 * Copyright 2014 K'naan.
 * Licensed under Apache (http://www.apache.org/licenses/LICENSE-2.0)
 * ======================================================================== */

+function ($) {
    'use strict';

    // BASEDRAG CLASS DEFINITION
    // ======================
    
    var Basedrag = function(element, options) {
        this.$element = $(element)
        this.options  = options
    }
    
    Basedrag.prototype.init = function() {
        var that = this
        
        this.options.$obj = this.$element
        if (this.options.obj) this.options.$obj = this.options.obj
        if (this.options.event)
            this.start(this.options.event)
        else
            this.$element.find(this.options.selector).bind('mousedown', function(e) { that.start.apply(that, [e]) })
    }
    
    Basedrag.prototype.start = function(e) {
        document.onselectstart = function(e) { return false } //禁止选择
        var that = this
        
        this.options.oleft = parseInt(this.$element.css('left')) || 0
        this.options.otop  = parseInt(this.$element.css('top')) || 0
        $(document).bind('mouseup.bjui.basedrag', function(e) { that.stop.apply(that, [e]) })
            .bind('mousemove.bjui.basedrag', function(e) { that.drag.apply(that, [e]) })
    }
    
    Basedrag.prototype.drag = function(e) {
        if (!e) e = window.event
        var options = this.options
        var left    = (options.oleft + (e.pageX || e.clientX) - options.event.pageX)
        var top     = (options.otop + (e.pageY || e.clientY) - options.event.pageY)
        
        if (top < 1) top = 0
        if (options.move == 'horizontal') {
            if ((options.minW && left >= parseInt(this.options.$obj.css('left')) + options.minW) && (options.maxW && left <= parseInt(this.options.$obj.css('left')) + options.maxW)) {
                this.$element.css('left', left)
            } else if (options.scop) {
                if (options.relObj) {
                    if ((left - parseInt(options.relObj.css('left'))) > options.cellMinW)
                        this.$element.css('left', left)
                    else
                        this.$element.css('left', left)
                }
            }
        } else if (options.move == 'vertical') {
            this.$element.css('top', top)
        } else {
            var $selector = options.selector ? this.options.$obj.find(options.selector) : this.options.$obj
            
            if (left >= -$selector.outerWidth() * 2 / 3 && top >= 0 && (left + $selector.outerWidth() / 3 < $(window).width()) && (top + $selector.outerHeight() < $(window).height())) {
                this.$element.css({left:left, top:top})
            }
        }
        if (options.drag)
            options.drag.apply(this.$element, [this.$element, e])
        
        return this.preventEvent(e)
    }
    
    Basedrag.prototype.stop = function(e) {
        $(document).unbind('mousemove.bjui.basedrag').unbind('mouseup.bjui.basedrag')
        if (this.options.stop)
            this.options.stop.apply(this.$element, [this.$element, e])
        if (this.options.event)
            this.destroy()
        document.onselectstart = function(e) { return true } //启用选择
        return this.preventEvent(e)
    }
    
    Basedrag.prototype.preventEvent = function(e) {
        if (e.stopPropagation) e.stopPropagation()
        if (e.preventDefault) e.preventDefault()
        return false
    }
    
    Basedrag.prototype.destroy = function() {
        this.$element.removeData('bjui.basedrag')
        if (!this.options.nounbind) this.$element.unbind('mousedown')
    }
    
    // BASEDRAG PLUGIN DEFINITION
    // =======================
    
    function Plugin(option) {
        var args     = arguments
        var property = option
        
        return this.each(function () {
            var $this   = $(this)
            var options = $.extend({}, $this.data(), typeof option == 'object' && option)
            var data    = $this.data('bjui.basedrag')
            
            if (!data) $this.data('bjui.basedrag', (data = new Basedrag(this, options)))
            if (typeof property == 'string' && $.isFunction(data[property])) {
                [].shift.apply(args)
                if (!args) data[property]()
                else data[property].apply(data, args)
            } else {
                data.init()
            }
        })
    }

    var old = $.fn.basedrag

    $.fn.basedrag             = Plugin
    $.fn.basedrag.Constructor = Basedrag
    
    // BASEDRAG NO CONFLICT
    // =================
    
    $.fn.basedrag.noConflict = function () {
        $.fn.basedrag = old
        return this
    }
    
}(jQuery);
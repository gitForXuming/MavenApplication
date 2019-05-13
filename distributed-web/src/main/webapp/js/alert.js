/* ==========================================================
 * bootstrap-alert.js v2.0.1
 * http://twitter.github.com/bootstrap/javascript.html#alerts
 * ==========================================================
 * Copyright 2012 Twitter, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ========================================================== */
!function ($) {

    "use strict"

    /* ALERT CLASS DEFINITION
     * ====================== */

    var dismiss = '[data-dismiss="alert"]'
        , Alert = function (el) {
        $(el).bind('click', dismiss, this.close)
    }

    Alert.prototype = {

        constructor: Alert

        , close: function (e) {
            var $this = $(this)
                , selector = $this.attr('data-target')
                , $parent
            if (!selector) {
                selector = $this.attr('href')
                selector = selector && selector.replace(/.*(?=#[^\s]*$)/, '') //strip for ie7
            }
            $parent = $(selector)
            $parent.trigger('close')

            e && e.preventDefault()

            $parent.length || ($parent = $this.hasClass('alert') ? $this : $this.parent())
            $parent.trigger('close').removeClass('in')

            function removeElement() {
                $parent.trigger('closed').hide()
            }

            $.support.transition && $parent.hasClass('fade') ? $parent.bind($.support.transition.end, hide) : $parent.hide()
        }
    }


    /* ALERT PLUGIN DEFINITION
     * ======================= */

    $.fn.alert = function (option) {
        return this.each(function () {
            var $this = $(this)
                , data = $this.data('alert')
            if (!data) $this.data('alert', (data = new Alert(this)))
            if (typeof option == 'string') data[option].call($this)
        })
    }

    $.fn.alert.Constructor = Alert

    /* ALERT DATA-API
     * ============== */

    $(function () {
        $('body').on('click.alert.data-api', dismiss, Alert.prototype.close)
    })
    $(function () {
        $(":text").on("input", function () {
            if ($(this).val() != null) {
                ($(this).hasClass('alertshow')) ? $(this).removeClass('alertshow') : null
            }
        })
    })

    /*click 是事件  alert.data-api 申明了 .alert .data-api 两个命令空间
     dismiss 要绑定到谁 如 div（包括 body 下的所有div）  li  input等
     id="alert" 指定单一绑定
     **/
}(window.jQuery);
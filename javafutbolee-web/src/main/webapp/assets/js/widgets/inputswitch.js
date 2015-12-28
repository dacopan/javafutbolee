/**
 * PrimeFaces InputSwitch Widget
 */
var idx = 1;
PrimeFaces.widget.InputSwitch = PrimeFaces.widget.DeferredWidget.extend({
    init: function (cfg) {
        this._super(cfg);

        this.onContainer = this.jq.children('.ui-inputswitch-on');
        this.onLabel = this.onContainer.children('span');
        this.offContainer = this.jq.children('.ui-inputswitch-off');
        this.offLabel = this.offContainer.children('span');
        this.handle = this.jq.children('.ui-inputswitch-handle');
        this.input = $(this.jqId + '_input');        
        this.renderDeferred();
        this.idx = idx++;
        //console.log('jfee: init '+ this.jqId + ' unique: ' + this.idx);
    },
    _render: function () {
        /*        var onContainerWidth = this.onContainer.width(),
         offContainerWidth = this.offContainer.width(),
         spanPadding = this.offLabel.innerWidth() - this.offLabel.width(),
         handleMargins = this.handle.outerWidth() - this.handle.innerWidth();
         
         var containerWidth = (onContainerWidth > offContainerWidth) ? onContainerWidth : offContainerWidth,
         handleWidth = containerWidth;
         */

        /*this.handle.css({'width': handleWidth});
         handleWidth = this.handle.width();
         
         containerWidth = containerWidth + handleWidth + 6;
         
         var labelWidth = containerWidth - handleWidth - spanPadding - handleMargins;
         
         this.jq.css({'width': containerWidth});
         this.onLabel.width(labelWidth);
         this.offLabel.width(labelWidth);
         
         //position
         this.offContainer.css({width: this.jq.width() - 5});
         this.offset = this.jq.width() - this.handle.outerWidth();
         */
        if (this.input.prop('checked')) {
            //this.handle.css({'left': this.offset});
            //this.onContainer.css({'width': this.offset});
            //this.offLabel.css({'margin-right': -this.offset});
            this.jq.addClass("ui-state-checked");
        } else {
            //this.onContainer.css({'width': 0});
            //this.onLabel.css({'margin-left': -this.offset});
            this.jq.removeClass("ui-state-checked");
        }
        //*/

        //console.log('jfee: _render '+ this.jqId + ' unique: ' + this.idx);
        if (!this.input.prop('disabled')) {
            this._bindEvents();
        }
    },
    _bindEvents: function () {
        var $this = this;
        //console.log('jfee: _bindEvents ' + $this.jqId + ' unique: ' + $this.idx);
        //prevent duplicates
        this.jq.off( ".inputSwitch" );
        this.jq.on('click.inputSwitch', function (e) {
            //console.log(e.target);
            //console.log('jfee: click {' + this.jq + '} ' + $this.jqId + ' unique: ' + $this.idx);
            //console.log($this.jq);
            $this.toggle();
            $this.input.trigger('focus');
        });

        this.input.on('focus.inputSwitch', function (e) {
            $this.handle.addClass('ui-state-focus');
        })
                .on('blur.inputSwitch', function (e) {
                    $this.handle.removeClass('ui-state-focus');
                })
                .on('keydown.inputSwitch', function (e) {
                    var keyCode = $.ui.keyCode;
                    if (e.which === keyCode.SPACE) {
                        e.preventDefault();
                    }
                })
                .on('keyup.inputSwitch', function (e) {
                    var keyCode = $.ui.keyCode;
                    if (e.which === keyCode.SPACE) {
                        $this.toggle();

                        e.preventDefault();
                    }
                })
                .on('change.inputSwitch', function (e) {
                    //console.log(e.target);
                    //console.log('jfee: change {' + $(e.target).attr("class") + '} ' + $this.jqId + ' unique: ' + $this.idx);
                    if ($this.input.prop('checked'))
                        $this._checkUI();
                    else
                        $this._uncheckUI();
                });
    },
    toggle: function () {
        //console.log('jfee: toogle ' + this.jqId);
        if (this.input.prop('checked'))
            this.uncheck();
        else
            this.check();
    },
    check: function () {
        //console.log('jfee: check ' + this.jqId);
        this.input.prop('checked', true).trigger('change');
    },
    uncheck: function () {
        //console.log('jfee: uncheck ' + this.jqId);
        this.input.prop('checked', false).trigger('change');
    },
    _checkUI: function () {
        //console.log('jfee: checkui ' + this.jqId);
        this.jq.addClass("ui-state-checked");
        //this.onContainer.animate({width: this.offset}, 200);
        //this.onLabel.animate({marginLeft: 0}, 200);
        //this.offLabel.animate({marginRight: -this.offset}, 200);
        //this.handle.animate({left: this.offset}, 200);
    },
    _uncheckUI: function () {
        //this.onContainer.animate({width: 0}, 200);
        //console.log('jfee: uncheckui ' + this.jqId);
        this.jq.removeClass("ui-state-checked");
        //this.onLabel.animate({marginLeft: -this.offset}, 200);
        //this.offLabel.animate({marginRight: 0}, 200);
        //this.handle.animate({left: 0}, 200);
    }
});
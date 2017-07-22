define([
    "dojo/_base/declare",
    "dijit/_WidgetBase",
    "dijit/_TemplatedMixin",
    "dgrid/Grid",
    "dojo/text!stocksim/pages/list-stocks-page/ListStocks.html"
], function(declare, _WidgetBase, _TemplatedMixin, Grid, template) {

    return declare([_WidgetBase, _TemplatedMixin], {
        templateString: template,

        startup: function() {
            var columns = [
                {
                    field: 'first',
                    label: 'First Name'
                },
                {
                    field: 'last',
                    label: 'Last Name'
                },
                {
                    field: 'age',
                    label: 'Age'
                }
            ];

            let grid = new Grid({
                columns: columns
            }, this.gridAttachPoint);
            grid.startup();
        }
    });

});
(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["designerModule1","metadata1","panel_metadata1"],{"0e6c":function(t,e,a){"use strict";a.r(e),e["default"]={metadata:{tableName:"",listTable:{},listColumns:[],showSearch:!0,searchColumns:[],rowSelection:"NONE"},scriptConfig:{data:[{key:"customQueryParam",name:"查询对象",type:"Object"},{key:"selectedRowKeys",name:"列表选择数据列表",type:"Array"}],methods:[{key:"getList",name:"获取列表",params:[]}],event:[],emitEvent:[{key:"selectedRowKeys",name:"列表数据选择事件",params:[{key:"selectedRowKeys",name:"选择数据key列表",type:"Array"}]},{key:"selectedRows",name:"列表数据选择对象列表事件",params:[{key:"selectedRows",name:"选择数据对象列表",type:"Array"}]}]}}},"54ed":function(t,e,a){"use strict";a.r(e),e["default"]={metadata:{columns:[]}}},"7f84":function(t,e,a){"use strict";a("8e6e"),a("ac6a"),a("456d");var n=a("bd86"),o=a("2f62");function i(t,e){var a=Object.keys(t);if(Object.getOwnPropertySymbols){var n=Object.getOwnPropertySymbols(t);e&&(n=n.filter((function(e){return Object.getOwnPropertyDescriptor(t,e).enumerable}))),a.push.apply(a,n)}return a}function r(t){for(var e=1;e<arguments.length;e++){var a=null!=arguments[e]?arguments[e]:{};e%2?i(Object(a),!0).forEach((function(e){Object(n["a"])(t,e,a[e])})):Object.getOwnPropertyDescriptors?Object.defineProperties(t,Object.getOwnPropertyDescriptors(a)):i(Object(a)).forEach((function(e){Object.defineProperty(t,e,Object.getOwnPropertyDescriptor(a,e))}))}return t}var s=Object(o["a"])("panelDesigner"),c=s.mapMutations;e["a"]={methods:r({},c(["removeConfig"])),computed:{moduleConfig:function(){if(this.distModuleConfig)return this.distModuleConfig;var t=this.$store.state.panelDesigner[this.code].moduleConfigMap&&this.uuid&&this.$store.state.panelDesigner[this.code].moduleConfigMap[this.uuid];return t||this.metadata}},props:{uuid:{type:String,required:!0},distModuleConfig:{type:Object},code:{type:String,required:!0},projectType:{type:String,required:!0}},beforeDestroy:function(){this.moduleConfig&&this.moduleConfig.uuid&&this.removeConfig({key:this.code,uuid:this.moduleConfig.uuid})}}},"849b":function(t,e,a){},b13d:function(t,e,a){"use strict";a.r(e);var n=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"general-list",staticStyle:{padding:"10px","padding-bottom":"25px"}},[a("a-row",{attrs:{gutter:36}},[a("a-col",{attrs:{span:24}},[t.moduleConfig.metadata.showSearch?a("div",{staticStyle:{padding:"10px","padding-bottom":"25px"}},[a("a-form",{attrs:{layout:"inline"}},[t.moduleConfig.metadata.searchColumns&&t.moduleConfig.metadata.searchColumns.length>0?a("a-row",{attrs:{gutter:18}},[t._l(t.moduleConfig.metadata.searchColumns,(function(e,n){return a("a-col",{key:n,attrs:{md:8,sm:24}},[a("a-form-item",{staticStyle:{width:"100%"},attrs:{label:e.fieldLabel,labelAlign:"right",labelCol:{span:6},wrapperCol:{span:18}}},["S_SELECT"===e.columnExt.formType?a("a-select",{staticStyle:{width:"100%"},attrs:{placeholder:"请选择"+e.fieldLabel}},[a("a-select-option",{attrs:{value:""}},[t._v("\n                    示例数据\n                  ")])],1):"SWITCH"===e.columnExt.formType?a("a-select",{staticStyle:{width:"100%"},attrs:{placeholder:"请选择"+e.fieldLabel}},[a("a-select-option",{attrs:{value:""}},[t._v("\n                    是\n                  ")]),a("a-select-option",{attrs:{value:""}},[t._v("\n                    否\n                  ")])],1):"Date"===e.fieldPackagingType||"DATETIMEPICKER"===e.columnExt.formType||"DATEPICKER"===e.columnExt.formType?["BETWEEN"===e.columnExt.searchType?a("a-range-picker",{staticStyle:{width:"100%"}}):a("a-date-picker",{staticStyle:{width:"100%"},attrs:{placeholder:e.fieldLabel}})]:"INPUT_NUMBER"===e.columnExt.formType?a("a-input-number",{staticStyle:{width:"100%"},attrs:{placeholder:"请输入"+e.fieldLabel}}):a("a-input",{staticStyle:{width:"100%"},attrs:{placeholder:e.fieldLabel}})],2)],1)})),a("a-col",{attrs:{md:t.moduleConfig.metadata.searchColumns.length>2?24:8,sm:24}},[a("span",{staticClass:"table-page-search-submitButtons",staticStyle:{float:"right"}},[a("a-button",{attrs:{type:"primary",icon:"search"}},[t._v("查询")]),a("a-button",{staticStyle:{"margin-left":"8px"},attrs:{icon:"redo"}},[t._v("重置")]),t.moduleConfig.metadata.searchColumns.length>2?a("a",{staticStyle:{"margin-left":"8px"}},[t._v("\n                  收起\n                  "),a("a-icon",{attrs:{type:"up"}})],1):t._e()],1)])],2):t._e()],1)],1):t._e(),a("a-table",{ref:"table",attrs:{size:"default","row-selection":t.rowSelection,columns:t.columns,dataSource:t.dataList,rowKey:"id"}})],1)],1)],1)},o=[],i=a("7618"),r=(a("ac6a"),a("7f7f"),a("7f84")),s=a("0e6c"),c={name:"GeneralList",data:function(){return{metadata:s["default"],dataList:[],columns:[],boxHeight:320}},methods:{autoSetHeight:function(){var t=document.querySelectorAll(".general-list");if(t&&t.length){var e=t[0].offsetHeight;console.log("height",e),this.boxHeight=e-25}}},computed:{rowSelection:function(){return"NONE"===this.moduleConfig.metadata.rowSelection?null:{onChange:function(t,e){console.log("selectedRowKeys: ".concat(t),"selectedRows: ",e)},getCheckboxProps:function(t){return{props:{disabled:!1,name:t.name}}}}}},watch:{"moduleConfig.metadata.listColumns":{handler:function(t){var e=this,a={},n=[];t&&t.length>0&&(t.forEach((function(t){var e=t.example;if(("Date"===t.fieldPackagingType||t.columnExt&&"DATETIMEPICKER"===t.columnExt.formType||t.columnExt&&"DATEPICKER"===t.columnExt.formType)&&t.example&&"object"===Object(i["a"])(t.example)){var o="DATETIMEPICKER"===t.columnExt.formType?"YYYY-MM-DD hh:mm":"YYYY-MM-DD";e=t.example.format(o)}a[t.fieldName]=e||"示例数据";var r={title:t.fieldLabel,dataIndex:t.fieldName};n.push(r)})),this.columns=n,this.dataList=[a]),setTimeout((function(){e.autoSetHeight()}),300)},immediate:!0}},mixins:[r["a"]]},l=c,u=(a("f7f8"),a("2877")),d=Object(u["a"])(l,n,o,!1,null,"0905e35a",null);e["default"]=d.exports},c00a:function(t,e,a){"use strict";e["a"]={computed:{moduleConfig:function(){if(this.distModuleConfig)return this.distModuleConfig;var t=this.$store.state.DragConfig[this.code].moduleConfigMap&&this.uuid&&this.$store.state.DragConfig[this.code].moduleConfigMap[this.uuid];return t||this.metadata}},props:{uuid:{type:String,required:!0},distModuleConfig:{type:Object},code:{type:String,required:!0},projectType:{type:String,required:!0}}}},c902:function(t,e,a){},e832:function(t,e,a){"use strict";a.r(e);var n=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticStyle:{padding:"10px","padding-bottom":"25px"}},[a("a-table",{ref:"table",attrs:{size:"default",columns:t.columns,dataSource:t.dataList,rowKey:"id"}})],1)},o=[],i=(a("8e6e"),a("456d"),a("bd86")),r=(a("ac6a"),a("c00a")),s=a("54ed"),c=a("2f62");function l(t,e){var a=Object.keys(t);if(Object.getOwnPropertySymbols){var n=Object.getOwnPropertySymbols(t);e&&(n=n.filter((function(e){return Object.getOwnPropertyDescriptor(t,e).enumerable}))),a.push.apply(a,n)}return a}function u(t){for(var e=1;e<arguments.length;e++){var a=null!=arguments[e]?arguments[e]:{};e%2?l(Object(a),!0).forEach((function(e){Object(i["a"])(t,e,a[e])})):Object.getOwnPropertyDescriptors?Object.defineProperties(t,Object.getOwnPropertyDescriptors(a)):l(Object(a)).forEach((function(e){Object.defineProperty(t,e,Object.getOwnPropertyDescriptor(a,e))}))}return t}var d={name:"ExcelExport",data:function(){return{metadata:s["default"],dataList:[],columns:[]}},methods:{convertColumnsAndDataList:function(t){var e={},a=[];t&&t.length>0&&(t.forEach((function(t){e[t.fieldName]=t.example;var n={title:t.fieldLabel,dataIndex:t.fieldName};a.push(n)})),this.columns=a,this.dataList=[e])}},computed:u({},Object(c["f"])({funcList:function(t){return t.DragConfig.commonConfig.funcList}})),watch:{"moduleConfig.metadata.columns":function(t){this.convertColumnsAndDataList(t)}},mixins:[r["a"]],mounted:function(){this.$nextTick((function(){this.moduleConfig.metadata.columns&&this.convertColumnsAndDataList(this.moduleConfig.metadata.columns)}))}},f=d,m=(a("fccf"),a("2877")),p=Object(m["a"])(f,n,o,!1,null,"4ed88864",null);e["default"]=p.exports},f7f8:function(t,e,a){"use strict";a("849b")},fccf:function(t,e,a){"use strict";a("c902")}}]);
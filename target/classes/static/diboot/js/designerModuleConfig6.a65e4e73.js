(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["designerModuleConfig6"],{"01f0":function(e,t,a){},"085b":function(e,t,a){"use strict";var n=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"props-kit"},[a("a-collapse",{attrs:{"default-active-key":"0",bordered:!1},model:{value:e.panelActiveKey,callback:function(t){e.panelActiveKey=t},expression:"panelActiveKey"}},[e._l(e.scriptConfig.props,(function(t,n){return[a("a-collapse-panel",{key:"props_panel_"+n,staticStyle:{"padding-left":"0"},attrs:{header:t.key}},[a("a-row",[a("a-col",{attrs:{span:18,offset:2}},[a("a-form-item",{staticStyle:{"margin-bottom":"0"},attrs:{label:"是否启用该参数",labelCol:{span:16}}},[a("a-switch",{attrs:{"checked-children":"是","un-checked-children":"否"},model:{value:t.open,callback:function(a){e.$set(t,"open",a)},expression:"prop.open"}})],1)],1)],1),t.open?a("a-row",[a("a-col",{attrs:{span:18,offset:2}},[a("a-form-item",{staticClass:"form-line",staticStyle:{"margin-bottom":"0"}},[a("a-select",{staticStyle:{width:"100%"},attrs:{placeholder:"请选择绑定参数",showSearch:"",filterOption:function(a,n){return e.filterOptionAndSetValue(t,a,n)},size:"small"},model:{value:t.targetData,callback:function(a){e.$set(t,"targetData",a)},expression:"prop.targetData"}},[a("a-select-option",{attrs:{value:""}},[e._v("\n                  请选择绑定参数\n                ")]),e._l(e.scriptDataList,(function(t,n){return a("a-select-option",{key:n,attrs:{value:t.key}},[e._v("\n                  "+e._s(t.key)+" ["+e._s(t.name)+"]\n                ")])}))],2)],1)],1)],1):e._e(),t.open?a("a-row",[a("a-col",{attrs:{span:18,offset:2}},[a("a-form-item",{staticClass:"form-line",staticStyle:{"margin-bottom":"0"}},[a("a-input",{attrs:{size:"small",placeholder:"或输入绑定参数"},model:{value:t.targetData,callback:function(a){e.$set(t,"targetData",a)},expression:"prop.targetData"}})],1)],1)],1):e._e(),t.open?a("a-row",[a("a-col",{attrs:{span:18,offset:2}},[a("a-form-item",{staticStyle:{"margin-bottom":"0"},attrs:{label:"参数有值时显示",labelCol:{span:16}}},[a("a-switch",{attrs:{"checked-children":"是","un-checked-children":"否"},model:{value:t.onlyDataShow,callback:function(a){e.$set(t,"onlyDataShow",a)},expression:"prop.onlyDataShow"}})],1)],1)],1):e._e(),t.open?a("a-row",[a("a-col",{attrs:{span:18,offset:2}},[a("a-form-item",{staticStyle:{"margin-bottom":"0"},attrs:{label:"转String格式",labelCol:{span:16}}},[a("a-switch",{attrs:{"checked-children":"是","un-checked-children":"否"},model:{value:t.convertToString,callback:function(a){e.$set(t,"convertToString",a)},expression:"prop.convertToString"}})],1)],1)],1):e._e()],1)]}))],2)],1)},o=[],i=a("0561"),r={name:"PropsKit",data:function(){return{panelActiveKey:"props_panel_0"}},methods:{filterOptionAndSetValue:function(e,t,a){var n=a.componentOptions.children[0].text.toLowerCase();if(n.indexOf(t.toLowerCase())>=0)return!0;var o=a.componentOptions.propsData.value.toLowerCase();return o.indexOf(t.toLowerCase())>=0||(e.targetData=t,!1)}},mixins:[i["a"]],props:{code:{type:String,default:""},scriptConfig:{type:Object,default:function(){return{}}},workspace:{type:String,default:"panelDesigner"}}},c=r,l=(a("0c19"),a("2877")),s=Object(l["a"])(c,n,o,!1,null,"2e30a0bc",null);t["a"]=s.exports},"0c19":function(e,t,a){"use strict";a("e475")},"1ca6":function(e,t,a){"use strict";var n=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"event-kit"},[e.scriptConfig.event&&e.scriptConfig.event.length>0?a("a-collapse",{attrs:{"default-active-key":"0",bordered:!1},model:{value:e.panelActiveKey,callback:function(t){e.panelActiveKey=t},expression:"panelActiveKey"}},[e._l(e.scriptConfig.event,(function(t,n){return[a("a-collapse-panel",{key:"event_panel_"+n,staticStyle:{"padding-left":"0"},attrs:{header:"@"+t.key+"("+t.params.map((function(e){return e.key})).join(", ")+")"}},[a("a-row",[a("a-col",{attrs:{span:18,offset:2}},[a("a-switch",{attrs:{"checked-children":"启用","un-checked-children":"未启用"},model:{value:t.open,callback:function(a){e.$set(t,"open",a)},expression:"eve.open"}})],1)],1),t.open?a("a-popover",{attrs:{placement:"left",title:"@"+t.key+" 事件绑定",trigger:"click"}},[a("div",{staticStyle:{width:"600px",height:"400px"},attrs:{slot:"content"},slot:"content"},[a("smart-code-editor",{attrs:{code:e.code,workspace:e.workspace,"param-value-getter":function(a){return e.paramValueGetter(a,t.params,t.handlerCode)}},on:{input:function(a){return e.onHandlerCodeChange(a,t)}},model:{value:t.handlerCode,callback:function(a){e.$set(t,"handlerCode",a)},expression:"eve.handlerCode"}})],1),a("a-button",{staticStyle:{margin:"10px 0"},attrs:{block:"",type:"primary",size:"small"}},[e._v("\n            编辑事件函数\n          ")])],1):e._e()],1)]}))],2):[e._v("\n    无\n  ")]],2)},o=[],i=(a("28a5"),a("4917"),a("788b")),r=a("541c"),c={name:"EventKit",data:function(){return{panelActiveKey:"event_panel_0"}},methods:{paramValueGetter:function(e,t,a){var n="";if(t&&t.length>0&&a){for(var o,i=0;i<t.length;i++)if(t[i].type===e.type){o=i;break}if(void 0===o)return"";var r=/[^(][a-zA-Z0-9]+(?=\))/,c=a.match(r);if(0===!c.length)return"";var l=c[0].split(",");if(l.length<=o)return"";n=l[o].trim()}return n},onHandlerCodeChange:function(e,t){t.handlerFuncName=Object(r["a"])(e)}},components:{smartCodeEditor:i["a"]},props:{workspace:{type:String,default:"panelDesigner"},code:{type:String,default:""},scriptConfig:{type:Object,default:function(){return{}}},extraMethodObjList:{type:Array,default:function(){return[]}}}},l=c,s=(a("8d31"),a("2877")),p=Object(s["a"])(l,n,o,!1,null,"00f37324",null);t["a"]=p.exports},3319:function(e,t,a){"use strict";a.r(t);var n=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",[a("panel-data-config",{attrs:{code:e.code},model:{value:e.moduleConfig,callback:function(t){e.moduleConfig=t},expression:"moduleConfig"}})],1)},o=[],i=a("7f57"),r=a("409c"),c=a("6892"),l={name:"PanelDataConfig",mixins:[i["a"]],methods:{validateComplete:function(){}},components:{panelDataConfig:r["a"]},watch:{"moduleConfig.scriptConfig.data":{handler:function(e){c["a"].$emit("setGeneralDataConfig_".concat(this.code),this.moduleConfig)},deep:!0,immediate:!0}}},s=l,p=a("2877"),u=Object(p["a"])(s,n,o,!1,null,"f2192214",null);t["default"]=u.exports},3623:function(e,t,a){},"409c":function(e,t,a){"use strict";var n=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",[a("a-form",{staticClass:"panel-props-config ",attrs:{layout:"horizontal"}},[a("h3",{staticClass:"title separator"},[e._v("\n      面板data参数配置\n      "),a("a-icon",{staticStyle:{cursor:"pointer",color:"rgb(24, 144, 255)"},attrs:{type:"plus-circle"},on:{click:e.addProp}})],1),a("a-collapse",{attrs:{"default-active-key":"0",bordered:!1},model:{value:e.panelActiveKey,callback:function(t){e.panelActiveKey=t},expression:"panelActiveKey"}},[e._l(e.moduleConfig.scriptConfig.data,(function(t,n){return[a("a-collapse-panel",{key:"config_panel_"+n,attrs:{header:t.key?t.key:"参数"+(n+1)+" 配置"}},[a("a-form-item",{staticStyle:{"margin-bottom":"0"},attrs:{labelCol:{span:6},wrapperCol:{span:18},label:"参数"}},[a("a-input",{staticStyle:{width:"100%"},attrs:{size:"small",placeholder:"参数名称"},model:{value:t.key,callback:function(a){e.$set(t,"key",a)},expression:"config.key"}})],1),a("a-form-item",{staticStyle:{"margin-bottom":"0"},attrs:{labelCol:{span:6},wrapperCol:{span:18},label:"说明"}},[a("a-input",{staticStyle:{width:"100%"},attrs:{size:"small",placeholder:"参数说明"},model:{value:t.name,callback:function(a){e.$set(t,"name",a)},expression:"config.name"}})],1),a("a-form-item",{staticStyle:{"margin-bottom":"0"},attrs:{labelCol:{span:6},wrapperCol:{span:18},label:"类型"}},[a("a-select",{staticStyle:{width:"100%"},attrs:{placeholder:"参数类型",size:"small"},model:{value:t.type,callback:function(a){e.$set(t,"type",a)},expression:"config.type"}},[a("a-select-option",{attrs:{value:"String"}},[e._v("\n                String\n              ")]),a("a-select-option",{attrs:{value:"Array"}},[e._v("\n                Array\n              ")]),a("a-select-option",{attrs:{value:"Object"}},[e._v("\n                Object\n              ")]),a("a-select-option",{attrs:{value:"Function"}},[e._v("\n                Function\n              ")]),a("a-select-option",{attrs:{value:"Number"}},[e._v("\n                Number\n              ")]),a("a-select-option",{attrs:{value:"Boolean"}},[e._v("\n                Boolean\n              ")])],1)],1),a("a-form-item",{staticStyle:{"margin-bottom":"0"},attrs:{labelCol:{span:6},wrapperCol:{span:18},label:"默认值"}},[a("a-input",{staticStyle:{width:"100%"},attrs:{size:"small",placeholder:"参数默认值"},model:{value:t.default,callback:function(a){e.$set(t,"default",a)},expression:"config.default"}})],1),a("a-form-item",{staticStyle:{"margin-bottom":"0"},attrs:{labelCol:{span:10},wrapperCol:{span:14},label:"参数变化检测"}},[a("a-switch",{attrs:{"checked-children":"启用","un-checked-children":"不启用"},model:{value:t.hasWatch,callback:function(a){e.$set(t,"hasWatch",a)},expression:"config.hasWatch"}})],1),t.hasWatch?a("a-form-item",{staticStyle:{"margin-bottom":"0"},attrs:{labelCol:{span:10},wrapperCol:{span:14},label:"参数检测编辑"}},[a("a-popover",{attrs:{title:"参数检测代码编辑",placement:"left",trigger:"click"}},[a("template",{slot:"content"},[a("smart-code-editor",{attrs:{code:e.code},model:{value:t.watchCode,callback:function(a){e.$set(t,"watchCode",a)},expression:"config.watchCode"}})],1),a("a-button",{attrs:{type:"link"}},[e._v("\n                设置\n              ")])],2)],1):e._e(),a("a-form-item",{staticStyle:{"margin-bottom":"0"},attrs:{labelCol:{span:6},wrapperCol:{span:18},label:"操作"}},[a("a-button",{staticStyle:{"margin-right":"8px"},attrs:{size:"small",type:"primary",icon:"copy"},on:{click:function(t){return e.copyProp(n)}}},[e._v("\n              复制\n            ")]),a("a-popconfirm",{attrs:{placement:"topLeft",title:"是否确认删除该按钮？","ok-text":"是","cancel-text":"否"},on:{confirm:function(t){return e.removeProp(n)}}},[a("a-button",{attrs:{size:"small",type:"danger",icon:"delete"}},[e._v("\n                删除\n              ")])],1)],1)],1)]}))],2)],1)],1)},o=[],i=a("b57a"),r=a("788b"),c=a("2ef0"),l=a.n(c),s={key:"data",name:"Data参数",type:"String",default:"",hasWatch:!1,watchCode:"function(val) {\n  // 数据更改处理流程\n}"},p={name:"PanelDataConfig",data:function(){return{panelActiveKey:"config_panel_0",panelList:[]}},methods:{addProp:function(){this.moduleConfig.scriptConfig.data.push(l.a.cloneDeep(s)),this.panelActiveKey="config_panel_".concat(this.moduleConfig.scriptConfig.data.length-1)},copyProp:function(e){var t=l.a.cloneDeep(this.moduleConfig.scriptConfig.data[e]);this.moduleConfig.scriptConfig.data.splice(e,0,t),this.panelActiveKey="config_panel_".concat(e+1)},removeProp:function(e){this.moduleConfig.scriptConfig.data.splice(e,1),e>0?this.panelActiveKey="config_panel_".concat(e-1):this.moduleConfig.scriptConfig.data.length>0&&(this.panelActiveKey="config_panel_".concat(0))}},mixins:[i["a"]],components:{smartCodeEditor:r["a"]},created:function(){}},u=p,d=(a("f01d"),a("2877")),f=Object(d["a"])(u,n,o,!1,null,"3188e369",null);t["a"]=f.exports},"4ead":function(e,t,a){"use strict";a("e04b")},"541c":function(e,t,a){"use strict";a.d(t,"a",(function(){return n}));a("28a5");var n=function(e){if(!e)return"";var t=e.slice(0,e.indexOf("(")).split(" ");return 1===t.length?t[0].trim():t[t.length-1].trim()}},"6c8b":function(e,t,a){"use strict";a.r(t);var n=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",[a("paddingc-config",{attrs:{code:e.code},model:{value:e.moduleConfig,callback:function(t){e.moduleConfig=t},expression:"moduleConfig"}})],1)},o=[],i=a("e5b5"),r=a("9a1d"),c={name:"Paddingc",mixins:[i["a"]],components:{paddingcConfig:r["a"]}},l=c,s=(a("4ead"),a("2877")),p=Object(s["a"])(l,n,o,!1,null,"375e07eb",null);t["default"]=p.exports},"7f57":function(e,t,a){"use strict";a("8e6e"),a("ac6a"),a("456d");var n=a("bd86"),o=a("2ef0"),i=a.n(o),r=a("1ca6"),c=a("2f62"),l=a("80da");function s(e,t){var a=Object.keys(e);if(Object.getOwnPropertySymbols){var n=Object.getOwnPropertySymbols(e);t&&(n=n.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),a.push.apply(a,n)}return a}function p(e){for(var t=1;t<arguments.length;t++){var a=null!=arguments[t]?arguments[t]:{};t%2?s(Object(a),!0).forEach((function(t){Object(n["a"])(e,t,a[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(a)):s(Object(a)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(a,t))}))}return e}var u=Object(c["a"])("panelDesigner"),d=u.mapMutations;t["a"]={data:function(){return{workspace:"panelDesigner"}},methods:p(p({},d(["updateModuleConfigList","updateConfigMap"])),{},{filterOption:function(e,t){return t.componentOptions.children[0].text.toLowerCase().indexOf(e.toLowerCase())>=0}}),computed:{moduleConfig:{get:function(){var e=this.$store.state.panelDesigner[this.code].moduleConfigMap;return e[this.uuid]},set:function(e){var t=this.$store.state.panelDesigner[this.code].moduleConfigMap;t[this.uuid];i.a.cloneDeep(e),this.updateConfigMap({key:this.code,configMap:t})}}},components:{eventKit:r["a"]},mixins:[l["a"]],props:{uuid:{type:String,required:!0},code:{type:String,required:!0},projectType:{type:String,required:!0}}}},"8d31":function(e,t,a){"use strict";a("9913")},9913:function(e,t,a){},"9a1d":function(e,t,a){"use strict";var n=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("a-form",{attrs:{layout:"horizontal"}},[a("h3",{staticClass:"title"},[e._v("\n    间隔高度\n  ")]),a("a-row",[a("a-col",{attrs:{span:23}},[a("a-form-item",{staticClass:"form-line"},[a("a-slider",{attrs:{id:"padding-slider",defaultValue:36},model:{value:e.moduleConfig.metadata.height,callback:function(t){e.$set(e.moduleConfig.metadata,"height",t)},expression:"moduleConfig.metadata.height"}})],1)],1)],1)],1)},o=[],i=a("b57a"),r={name:"Paddingc",mixins:[i["a"]]},c=r,l=(a("9e61"),a("2877")),s=Object(l["a"])(c,n,o,!1,null,"e513674e",null);t["a"]=s.exports},"9e61":function(e,t,a){"use strict";a("3623")},b57a:function(e,t,a){"use strict";var n=a("085b"),o=a("1ca6");t["a"]={data:function(){return{moduleConfig:{}}},watch:{moduleConfig:{handler:function(e){this.$emit("input",e)},deep:!0,immediate:!0},value:{handler:function(e){this.moduleConfig=e},deep:!0,immediate:!0}},components:{propsKit:n["a"],eventKit:o["a"]},props:{workspace:{type:String,default:"panelDesigner"},code:{type:String,default:""},projectType:{type:String,required:!0},value:{type:Object,default:function(){return{}}}}}},e04b:function(e,t,a){},e475:function(e,t,a){},e5b5:function(e,t,a){"use strict";a("8e6e"),a("ac6a"),a("456d");var n=a("bd86"),o=a("2ef0"),i=a.n(o),r=a("2f62"),c=a("1ca6"),l=a("80da");function s(e,t){var a=Object.keys(e);if(Object.getOwnPropertySymbols){var n=Object.getOwnPropertySymbols(e);t&&(n=n.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),a.push.apply(a,n)}return a}function p(e){for(var t=1;t<arguments.length;t++){var a=null!=arguments[t]?arguments[t]:{};t%2?s(Object(a),!0).forEach((function(t){Object(n["a"])(e,t,a[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(a)):s(Object(a)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(a,t))}))}return e}var u=Object(r["a"])("DragConfig"),d=u.mapMutations;t["a"]={data:function(){return{workspace:"DragConfig"}},methods:p({},d(["updateModuleConfigList","updateConfigMap"])),computed:{moduleConfig:{get:function(){var e=this.$store.state.DragConfig[this.code].moduleConfigMap;return e[this.uuid]},set:function(e){var t=this.$store.state.DragConfig[this.code].moduleConfigMap;t[this.uuid];i.a.cloneDeep(e),this.updateConfigMap({key:this.code,configMap:t})}}},components:{eventKit:c["a"]},mixins:[l["a"]],props:{uuid:{type:String,required:!0},code:{type:String,required:!0},projectType:{type:String,required:!0}}}},f01d:function(e,t,a){"use strict";a("01f0")}}]);
(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["designerModuleConfig0~designerModuleConfig11~designerModuleConfig3~designerModuleConfig5~designerMod~f49b3302"],{"01bc":function(e,t,n){"use strict";n.d(t,"b",(function(){return i})),n.d(t,"c",(function(){return o})),n.d(t,"d",(function(){return r})),n.d(t,"a",(function(){return s}));var a=n("75fc"),r=(n("ac6a"),function(e){if(!e)return[];if(e.paramData){var t=JSON.parse(e.paramData);if(t.length>0)return t.forEach((function(e){e.open=!1,e.onlyDataShow=!1,e.convertToString=!1,e.targetData=""})),t}return[]}),i=function(e){if(e.eventData){var t=JSON.parse(e.eventData);if(t.length>0)return t.forEach((function(e){if(!e.key)return[];var t=e.key.slice(0,1).toUpperCase()+e.key.slice(1);e.open=!1,e.handlerFuncName="on".concat(t);var n="";e.params&&e.params.length>0&&(n=e.params.map((function(e){return e.key})).join(", ")),e.handlerCode="on".concat(t,"(").concat(n,") {\n  // 事件处理代码\n}")})),t}return[]},o=function(e,t){if(void 0===e)return[];var n=e.methodsData;if(!n)return[];var a=JSON.parse(n);return a.length>0&&t?(a.forEach((function(e){e.refName=t})),a):[]},s=function(e){var t=[],n=[];e.length>0&&(t=e.map((function(e){var t=o(e.panelConfig.panelInfo,e.panelConfig.refName);return t.length>0&&n.push.apply(n,Object(a["a"])(t)),{key:"close",name:"关闭该弹窗",params:[],refName:e.panelConfig.windowRefName}})));var r=[];return r.push.apply(r,Object(a["a"])(t)),r.push.apply(r,n),r}},"085b":function(e,t,n){"use strict";var a=function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",{staticClass:"props-kit"},[n("a-collapse",{attrs:{"default-active-key":"0",bordered:!1},model:{value:e.panelActiveKey,callback:function(t){e.panelActiveKey=t},expression:"panelActiveKey"}},[e._l(e.scriptConfig.props,(function(t,a){return[n("a-collapse-panel",{key:"props_panel_"+a,staticStyle:{"padding-left":"0"},attrs:{header:t.key}},[n("a-row",[n("a-col",{attrs:{span:18,offset:2}},[n("a-form-item",{staticStyle:{"margin-bottom":"0"},attrs:{label:"是否启用该参数",labelCol:{span:16}}},[n("a-switch",{attrs:{"checked-children":"是","un-checked-children":"否"},model:{value:t.open,callback:function(n){e.$set(t,"open",n)},expression:"prop.open"}})],1)],1)],1),t.open?n("a-row",[n("a-col",{attrs:{span:18,offset:2}},[n("a-form-item",{staticClass:"form-line",staticStyle:{"margin-bottom":"0"}},[n("a-select",{staticStyle:{width:"100%"},attrs:{placeholder:"请选择绑定参数",showSearch:"",filterOption:function(n,a){return e.filterOptionAndSetValue(t,n,a)},size:"small"},model:{value:t.targetData,callback:function(n){e.$set(t,"targetData",n)},expression:"prop.targetData"}},[n("a-select-option",{attrs:{value:""}},[e._v("\n                  请选择绑定参数\n                ")]),e._l(e.scriptDataList,(function(t,a){return n("a-select-option",{key:a,attrs:{value:t.key}},[e._v("\n                  "+e._s(t.key)+" ["+e._s(t.name)+"]\n                ")])}))],2)],1)],1)],1):e._e(),t.open?n("a-row",[n("a-col",{attrs:{span:18,offset:2}},[n("a-form-item",{staticClass:"form-line",staticStyle:{"margin-bottom":"0"}},[n("a-input",{attrs:{size:"small",placeholder:"或输入绑定参数"},model:{value:t.targetData,callback:function(n){e.$set(t,"targetData",n)},expression:"prop.targetData"}})],1)],1)],1):e._e(),t.open?n("a-row",[n("a-col",{attrs:{span:18,offset:2}},[n("a-form-item",{staticStyle:{"margin-bottom":"0"},attrs:{label:"参数有值时显示",labelCol:{span:16}}},[n("a-switch",{attrs:{"checked-children":"是","un-checked-children":"否"},model:{value:t.onlyDataShow,callback:function(n){e.$set(t,"onlyDataShow",n)},expression:"prop.onlyDataShow"}})],1)],1)],1):e._e(),t.open?n("a-row",[n("a-col",{attrs:{span:18,offset:2}},[n("a-form-item",{staticStyle:{"margin-bottom":"0"},attrs:{label:"转String格式",labelCol:{span:16}}},[n("a-switch",{attrs:{"checked-children":"是","un-checked-children":"否"},model:{value:t.convertToString,callback:function(n){e.$set(t,"convertToString",n)},expression:"prop.convertToString"}})],1)],1)],1):e._e()],1)]}))],2)],1)},r=[],i=n("0561"),o={name:"PropsKit",data:function(){return{panelActiveKey:"props_panel_0"}},methods:{filterOptionAndSetValue:function(e,t,n){var a=n.componentOptions.children[0].text.toLowerCase();if(a.indexOf(t.toLowerCase())>=0)return!0;var r=n.componentOptions.propsData.value.toLowerCase();return r.indexOf(t.toLowerCase())>=0||(e.targetData=t,!1)}},mixins:[i["a"]],props:{code:{type:String,default:""},scriptConfig:{type:Object,default:function(){return{}}},workspace:{type:String,default:"panelDesigner"}}},s=o,c=(n("0c19"),n("2877")),l=Object(c["a"])(s,a,r,!1,null,"2e30a0bc",null);t["a"]=l.exports},"0c19":function(e,t,n){"use strict";n("e475")},"1ca6":function(e,t,n){"use strict";var a=function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",{staticClass:"event-kit"},[e.scriptConfig.event&&e.scriptConfig.event.length>0?n("a-collapse",{attrs:{"default-active-key":"0",bordered:!1},model:{value:e.panelActiveKey,callback:function(t){e.panelActiveKey=t},expression:"panelActiveKey"}},[e._l(e.scriptConfig.event,(function(t,a){return[n("a-collapse-panel",{key:"event_panel_"+a,staticStyle:{"padding-left":"0"},attrs:{header:"@"+t.key+"("+t.params.map((function(e){return e.key})).join(", ")+")"}},[n("a-row",[n("a-col",{attrs:{span:18,offset:2}},[n("a-switch",{attrs:{"checked-children":"启用","un-checked-children":"未启用"},model:{value:t.open,callback:function(n){e.$set(t,"open",n)},expression:"eve.open"}})],1)],1),t.open?n("a-popover",{attrs:{placement:"left",title:"@"+t.key+" 事件绑定",trigger:"click"}},[n("div",{staticStyle:{width:"600px",height:"400px"},attrs:{slot:"content"},slot:"content"},[n("smart-code-editor",{attrs:{code:e.code,workspace:e.workspace,"param-value-getter":function(n){return e.paramValueGetter(n,t.params,t.handlerCode)}},on:{input:function(n){return e.onHandlerCodeChange(n,t)}},model:{value:t.handlerCode,callback:function(n){e.$set(t,"handlerCode",n)},expression:"eve.handlerCode"}})],1),n("a-button",{staticStyle:{margin:"10px 0"},attrs:{block:"",type:"primary",size:"small"}},[e._v("\n            编辑事件函数\n          ")])],1):e._e()],1)]}))],2):[e._v("\n    无\n  ")]],2)},r=[],i=(n("28a5"),n("4917"),n("788b")),o=n("541c"),s={name:"EventKit",data:function(){return{panelActiveKey:"event_panel_0"}},methods:{paramValueGetter:function(e,t,n){var a="";if(t&&t.length>0&&n){for(var r,i=0;i<t.length;i++)if(t[i].type===e.type){r=i;break}if(void 0===r)return"";var o=/[^(][a-zA-Z0-9]+(?=\))/,s=n.match(o);if(0===!s.length)return"";var c=s[0].split(",");if(c.length<=r)return"";a=c[r].trim()}return a},onHandlerCodeChange:function(e,t){t.handlerFuncName=Object(o["a"])(e)}},components:{smartCodeEditor:i["a"]},props:{workspace:{type:String,default:"panelDesigner"},code:{type:String,default:""},scriptConfig:{type:Object,default:function(){return{}}},extraMethodObjList:{type:Array,default:function(){return[]}}}},c=s,l=(n("8d31"),n("2877")),p=Object(l["a"])(c,a,r,!1,null,"00f37324",null);t["a"]=p.exports},"1f14":function(e,t,n){"use strict";n("5fe1")},"218b":function(e,t,n){"use strict";var a=function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",[n("h3",{staticClass:"title separator"},[e._v("面板选择")]),n("a-row",[n("a-col",{attrs:{span:23}},[e.panelList&&e.panelList.length>0?[n("a-form-model-item",{ref:"panelInfo",staticStyle:{"margin-bottom":"0"},attrs:{rules:e.rules.panelInfo}},[e.panelConfig&&e.panelConfig.panelInfo?n("a-select",{staticStyle:{width:"100%"},attrs:{"show-search":"","filter-option":e.filterOption,placeholder:"请选择一个面板"},on:{change:e.onPanelChanged},model:{value:e.panelConfig.panelInfo.id,callback:function(t){e.$set(e.panelConfig.panelInfo,"id",t)},expression:"panelConfig.panelInfo.id"}},e._l(e.panelList,(function(t){return n("a-select-option",{key:t.id,attrs:{value:t.id}},[e._v("\n              "+e._s(t.panelName)+" ["+e._s(t.panelCode)+"]\n            ")])})),1):e._e()],1)]:[e._v("\n        请先添加一个面板\n      ")],n("a-button-group",{attrs:{size:"small"}},[n("a-button",{attrs:{type:"link",disabled:e.panelBtnDisabled},on:{click:e.openPanelPage}},[n("a-icon",{attrs:{type:"plus"}}),e._v("添加/编辑面板")],1),n("a-button",{attrs:{type:"link"},on:{click:e.refreshPanelList}},[e._v(" 刷新"),n("a-icon",{attrs:{type:"redo"}})],1)],1)],2)],1),n("h3",{staticClass:"title separator"},[e._v("\n    当前面板编码\n  ")]),n("a-row",[n("a-col",{attrs:{span:23}},[n("a-form-model-item",{ref:"refName",staticStyle:{"margin-bottom":"0"},attrs:{rules:e.rules.refName}},[n("a-input",{attrs:{placeholder:"请输入当前页面唯一编码"},on:{change:e.extractPanelMethods},model:{value:e.panelConfig.refName,callback:function(t){e.$set(e.panelConfig,"refName",t)},expression:"panelConfig.refName"}})],1)],1)],1),e.panelConfig.scriptConfig&&e.panelConfig.scriptConfig.props&&e.panelConfig.scriptConfig.props.length>0?[n("h3",{staticClass:"title separator"},[e._v("入参配置")]),n("props-kit",{attrs:{code:e.code,workspace:e.workspace,"script-config":e.panelConfig.scriptConfig}})]:e._e(),e.panelConfig.scriptConfig&&e.panelConfig.scriptConfig.event&&e.panelConfig.scriptConfig.event.length>0?[n("h3",{staticClass:"title separator"},[e._v("组件事件处理")]),n("event-kit",{attrs:{code:e.code,workspace:e.workspace,"script-config":e.panelConfig.scriptConfig}})]:e._e(),n("panel-iframe",{ref:"panelIframe",on:{complete:e.onPanelCompleted}})],2)},r=[],i=(n("96cf"),n("3b8d")),o=(n("6762"),n("7f7f"),n("ac6a"),n("75fc")),s=(n("a481"),n("7514"),n("3aa3")),c=n("2ef0"),l=n.n(c),p=n("085b"),f=n("1ca6"),u=n("77db"),d=n("01bc"),g=function(){var e=this,t=e.$createElement,n=e._self._c||t;return e.visible?n("div",{staticClass:"iframe-wrapper"},[n("iframe",{staticStyle:{width:"100vw",height:"100vh"},attrs:{src:e.panelUrl,frameborder:"0"}}),n("a-button",{staticClass:"close-btn",attrs:{type:"danger",icon:"close"},on:{click:e.close}},[e._v("\n    关闭该弹出面板\n  ")])],1):e._e()},h=[],m=(n("28a5"),{name:"PanelIframe",data:function(){return{visible:!1,panelUrl:""}},methods:{open:function(){this.visible=!0},close:function(){this.visible=!1,this.$emit("close")}},created:function(){var e=window.location.href;this.panelUrl=e.split("#/")[0]+"#/panelDesignerPage";var t=this;window.panelIframeComplete=function(e){t.close(),t.$emit("complete",e)}},destroyed:function(){window.panelIframeComplete=void 0}}),v=m,C=(n("1f14"),n("2877")),b=Object(C["a"])(v,g,h,!1,null,"29aa4c93",null),y=b.exports,w={refName:"",panelInfo:{id:void 0,name:"",code:"",dirPath:"",aliasDirPath:"",componentName:"",fileName:"",paramData:"",eventData:"",methodsData:"",configData:""},scriptConfig:{props:[],methods:[],event:[],emitEvent:[]}},O={name:"GeneralPanelConfig",data:function(){return{panelBtnDisabled:!1,rules:{refName:[{required:!0,message:"请输入面板唯一编码",trigger:"blur"}],panelInfo:[{required:!0,message:"请选择一个面板",trigger:"blur"}]}}},methods:{openPanelPage:function(){window!==top?(this.$message.warning("请先完成当前面板！"),this.panelBtnDisabled=!0):this.$refs.panelIframe.open()},refreshPanelList:function(){var e=this;this.initPanelList().then((function(){e.$message.success("刷新面板列表成功")}))},onPanelChanged:function(e){if(0!==e){var t=this.panelList.find((function(t){return t.id===e}));void 0!==t&&(this.panelConfig.panelInfo={id:t.id,name:t.panelName,code:t.panelCode,dirPath:t.dirPath,fileName:t.fileName,componentName:Object(u["a"])(t.panelCode),aliasDirPath:t.dirPath.replace("src/","@/"),methodsData:t.methodsData,configData:t.configData},this.panelConfig.refName=t.panelCode,void 0!==this.appendExtraPanelConfig&&this.appendExtraPanelConfig(this.panelConfig),this.$emit("change",this.panelConfig),this.extractPanelEventKitData(t))}},extractPanelEventKitData:function(e){e&&(this.panelConfig.scriptConfig.props=Object(d["d"])(e),this.panelConfig.scriptConfig.event=Object(d["b"])(e),this.extractPanelMethods())},extractPanelMethods:function(e){var t=this,n=this.panelConfig.panelInfo,a=this.panelConfig.refName,r=Object(d["c"])(n,a);if(void 0!==this.extractPanelExtraMethods){var i=this.extractPanelExtraMethods();if(i=i.filter((function(e){return e.refName})),i&&i.length>0)r.push.apply(r,Object(o["a"])(i));else if(!0!==e)return setTimeout((function(){t.extractPanelMethods(!0)}),300),!1}var s=[],c=[];r.forEach((function(e){e.origin="panelExtractor";var t="".concat(e.key,"_").concat(e.name,"_").concat(e.refName);c.includes(t)||(s.push(e),c.push(t))})),this.panelConfig.scriptConfig.methods=s,this.$emit("change",this.panelConfig)},filterOption:function(e,t){return t.componentOptions.children[0].text.toLowerCase().indexOf(e.toLowerCase())>=0},initPanelList:function(){var e=Object(i["a"])(regeneratorRuntime.mark((function e(){var t;return regeneratorRuntime.wrap((function(e){while(1)switch(e.prev=e.next){case 0:return e.next=2,s["a"].get("/diboot/devtools/panelDesigner/getPanelList");case 2:t=e.sent,0===t.code&&this.$emit("updatePanelList",t.data);case 4:case"end":return e.stop()}}),e,this)})));function t(){return e.apply(this,arguments)}return t}(),onPanelCompleted:function(){var e=Object(i["a"])(regeneratorRuntime.mark((function e(t){var n,a;return regeneratorRuntime.wrap((function(e){while(1)switch(e.prev=e.next){case 0:return n=t.panelId,a=t.operateName,e.next=3,this.initPanelList();case 3:this.onPanelChanged(n),this.$message.success("面板".concat(a,"成功!"));case 5:case"end":return e.stop()}}),e,this)})));function t(t){return e.apply(this,arguments)}return t}()},watch:{panelConfig:{handler:function(e){this.$emit("change",e)},deep:!0}},mounted:function(){this.$nextTick((function(){void 0===this.panelList&&this.initPanelList(),this.panelConfig.panelInfo||this.$emit("change",l.a.cloneDeep(w))}))},components:{propsKit:p["a"],eventKit:f["a"],panelIframe:y},model:{prop:"panelConfig",event:"change"},props:{workspace:{type:String,default:"panelDesigner"},code:{type:String,default:""},panelConfig:{type:Object,required:!0},panelList:{type:Array,default:void 0},extractPanelExtraMethods:{type:Function,default:void 0},appendExtraPanelConfig:{type:Function,default:void 0}}},k=O,D=(n("ac47"),Object(C["a"])(k,a,r,!1,null,"a5f49d3c",null));t["a"]=D.exports},"541c":function(e,t,n){"use strict";n.d(t,"a",(function(){return a}));n("28a5");var a=function(e){if(!e)return"";var t=e.slice(0,e.indexOf("(")).split(" ");return 1===t.length?t[0].trim():t[t.length-1].trim()}},"5fe1":function(e,t,n){},"7f57":function(e,t,n){"use strict";n("8e6e"),n("ac6a"),n("456d");var a=n("bd86"),r=n("2ef0"),i=n.n(r),o=n("1ca6"),s=n("2f62"),c=n("80da");function l(e,t){var n=Object.keys(e);if(Object.getOwnPropertySymbols){var a=Object.getOwnPropertySymbols(e);t&&(a=a.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),n.push.apply(n,a)}return n}function p(e){for(var t=1;t<arguments.length;t++){var n=null!=arguments[t]?arguments[t]:{};t%2?l(Object(n),!0).forEach((function(t){Object(a["a"])(e,t,n[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(n)):l(Object(n)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(n,t))}))}return e}var f=Object(s["a"])("panelDesigner"),u=f.mapMutations;t["a"]={data:function(){return{workspace:"panelDesigner"}},methods:p(p({},u(["updateModuleConfigList","updateConfigMap"])),{},{filterOption:function(e,t){return t.componentOptions.children[0].text.toLowerCase().indexOf(e.toLowerCase())>=0}}),computed:{moduleConfig:{get:function(){var e=this.$store.state.panelDesigner[this.code].moduleConfigMap;return e[this.uuid]},set:function(e){var t=this.$store.state.panelDesigner[this.code].moduleConfigMap;t[this.uuid];i.a.cloneDeep(e),this.updateConfigMap({key:this.code,configMap:t})}}},components:{eventKit:o["a"]},mixins:[c["a"]],props:{uuid:{type:String,required:!0},code:{type:String,required:!0},projectType:{type:String,required:!0}}}},"87e4":function(e,t,n){},"8d31":function(e,t,n){"use strict";n("9913")},9913:function(e,t,n){},ac47:function(e,t,n){"use strict";n("87e4")},e475:function(e,t,n){},e5b5:function(e,t,n){"use strict";n("8e6e"),n("ac6a"),n("456d");var a=n("bd86"),r=n("2ef0"),i=n.n(r),o=n("2f62"),s=n("1ca6"),c=n("80da");function l(e,t){var n=Object.keys(e);if(Object.getOwnPropertySymbols){var a=Object.getOwnPropertySymbols(e);t&&(a=a.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),n.push.apply(n,a)}return n}function p(e){for(var t=1;t<arguments.length;t++){var n=null!=arguments[t]?arguments[t]:{};t%2?l(Object(n),!0).forEach((function(t){Object(a["a"])(e,t,n[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(n)):l(Object(n)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(n,t))}))}return e}var f=Object(o["a"])("DragConfig"),u=f.mapMutations;t["a"]={data:function(){return{workspace:"DragConfig"}},methods:p({},u(["updateModuleConfigList","updateConfigMap"])),computed:{moduleConfig:{get:function(){var e=this.$store.state.DragConfig[this.code].moduleConfigMap;return e[this.uuid]},set:function(e){var t=this.$store.state.DragConfig[this.code].moduleConfigMap;t[this.uuid];i.a.cloneDeep(e),this.updateConfigMap({key:this.code,configMap:t})}}},components:{eventKit:s["a"]},mixins:[c["a"]],props:{uuid:{type:String,required:!0},code:{type:String,required:!0},projectType:{type:String,required:!0}}}}}]);
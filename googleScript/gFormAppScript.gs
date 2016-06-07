/*
 * Author : HeidiHuang
 * Date : 2016/05/16
 * Send user data to server when submit.
 */

function sendHttpPost() {
    var eventObject = getFormResponse();
        var myJsonString = JSON.stringify(eventObject);
    var body =
    {
        "contentType" : "utf-8",
        "method"  : "post",
        "payload" : myJsonString,
        "muteHttpExceptions" : true,
    };
  
    var url = "http://demo.teddysoft.tw:8080/SLM2016/StudentAction?op=3";
    var result = UrlFetchApp.fetch(url, body);
    Logger.log(myJsonString);
 }
 

var GLOBAL = {
  formTitle : JSON.stringify(FormApp.getActiveForm().getTitle()),
  formId : FormApp.getActiveForm().getId(),  
  formMap : {
    ticket: "票種",
    name : "中文姓名",
    nickname : "您希望怎麼被稱呼？",
    email: "Email",
    cellphone: "手機",
    company : "公司",
    apartment : "部門",
    title : "職稱",
    vegeMeat : "用餐需求",
    receipt : "發票格式",
    companyTitle : "若選擇三聯式發票，請務必同時提供「公司抬頭」與「統編」",
    classInfo : "請問您如何得知本課程消息？",
    hasScrum : "請問您目前所在公司或組織是否有使用Scrum軟體開發流程？",
    check : "請注意，請先填寫線上報名表，當確認開課後您將收到匯款通知，屆時再行繳費即完成報名",
    teamMembers : "若您報名四人團報票，請填寫所有團報人員姓名，以供辨識。",
    comment : "有話想對主辦單位說嗎？歡迎留言！"
  },
}


function getFormResponse(e) {

  
  var form = FormApp.openById(GLOBAL.formId),
      responses = form.getResponses(),
      length = responses.length,
      lastResponse = responses[length-1],
      itemResponses = lastResponse.getItemResponses(),
  
  eventObject = {};
  
  for (var i = 0, x = itemResponses.length; i<x; i++) {
    var thisItem = itemResponses[i].getItem().getTitle(),
        thisResponse = itemResponses[i].getResponse();

    switch (thisItem) {     
      case GLOBAL.formMap.ticket:
        eventObject.ticket = thisResponse;
        break;
      case GLOBAL.formMap.name:
        eventObject.name = thisResponse;
        break;
      case GLOBAL.formMap.nickname:
        eventObject.nickname = thisResponse;
        break;
      case GLOBAL.formMap.email:
        eventObject.email = thisResponse;
        break;
      case GLOBAL.formMap.cellphone:
        eventObject.cellphone = thisResponse;
        break;
      case GLOBAL.formMap.company:
        eventObject.company = thisResponse;
        break;
      case GLOBAL.formMap.apartment:
        eventObject.apartment = thisResponse;
        break;
      case GLOBAL.formMap.title:
        eventObject.title = thisResponse;
        break;
      case GLOBAL.formMap.vegeMeat:
        eventObject.vegeMeat = thisResponse;
        break;
      case GLOBAL.formMap.receipt:
        eventObject.receipt = thisResponse;
        break;

      case GLOBAL.formMap.companyTitle:
        eventObject.companyTitle = thisResponse;
        break;
      case GLOBAL.formMap.classInfo:
        eventObject.classInfo = thisResponse;
        break;
      case GLOBAL.formMap.hasScrum:
        eventObject.hasScrum = thisResponse;
        break;
      case GLOBAL.formMap.check:
        eventObject.check = thisResponse;
        break;
      case GLOBAL.formMap.teamMembers:
        eventObject.teamMembers = thisResponse;
         break;
      case GLOBAL.formMap.comment:
        eventObject.comment = thisResponse;
        break;
    } 
  }    
  eventObject.courseName = FormApp.getActiveForm().getTitle();
  eventObject.batch = FormApp.getActiveForm().getDescription();
  return eventObject;
}



{
 "success":"${success}"
  <#if success = false>
      ,
      "errorCode":"${errorCode}",
      "message":"${message}"
  </#if>
}
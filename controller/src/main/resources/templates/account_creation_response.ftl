{
  "success": ${success}
  <#if success = false>
      ,
      "errorCode":"${errorCode}"
  </#if>
}
{
 "success": ${success},
 <#if success = true>
     "token": "${token}"
     <#else>
     "errorCode":"${errorCode}",
     "message":"${message}"
 </#if>
}
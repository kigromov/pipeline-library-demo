#!/usr/bin/env groovy
import hudson.tasks.test.AbstractTestResultAction
import hudson.tasks.junit.CaseResult

def call(Map<String, String> params) 
{
   def testList = params["testList"]
   def currentBuild
   def result
   for (int i = 0; i < 2; i++) 
   {
      stage("start test job ${i}") 
        {
           currentBuild = runTestJob(testList: testList)
           def testResultAction = currentBuild.rawBuild.getAction(AbstractTestResultAction.class)
           def failedTests = testResultAction.getFailedTests()
           testList = ""
           for(CaseResult cr : failedTests) {
               testList = testList + "FullyQualifiedName~${cr.getDisplayName()} | "
           }
         result = testList.substring(0, testList.length() - 3);
         testList = "--filter " + "\"" + result + "\""
         echo result
        }
   }
}


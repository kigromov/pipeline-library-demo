#!/usr/bin/env groovy
import hudson.tasks.test.AbstractTestResultAction
import hudson.tasks.junit.CaseResult
def call(Map<String, String> params) {
   def testList = params["testList"]

       echo testList
       echo "START 1 JOB"
      stage("start test job 1") 
    {
       def currentBuild = runTestJob(testList: testList)
    }
       echo "END 1 JOB"
       
       def testResultAction = currentBuild.rawBuild.getAction(AbstractTestResultAction.class)
       def failedTests = testResultAction.getFailedTests()
       for(CaseResult cr : failedTests) {
           testList = testList + "${cr.getFullDisplayName()},"
        }
       echo testList
       
       echo "START 2 JOB"
      stage("start test job 2") 
    {
       currentBuild  = runTestJob(testList: testList)
    }
       echo "END 2 JOB"
                  
}


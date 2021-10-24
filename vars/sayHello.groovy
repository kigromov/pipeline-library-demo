#!/usr/bin/env groovy
import hudson.tasks.test.AbstractTestResultAction
import hudson.tasks.junit.CaseResult
def call(String name = 'human') {
   stage("start test job") 
    {
       echo "START 1 JOB"
       def cBuild = runTestJob()
       echo "END 1 JOB"
       
       def testResultAction = cBuild.rawBuild.getAction(AbstractTestResultAction.class)
       def failedTests = testResultAction.getFailedTests()
       def testList = "```"
       for(CaseResult cr : failedTests) {
           testList = testList + "${cr.getFullDisplayName()}:\n${cr.getErrorDetails()}\n\n"
        }
       echo testList
       
       echo "START 2 JOB"
       def cBuild = runTestJob(testList: testList)
       echo "END 2 JOB"
       
    }
            
}


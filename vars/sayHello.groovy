#!/usr/bin/env groovy
import hudson.tasks.test.AbstractTestResultAction
import hudson.tasks.junit.CaseResult
def call(String name = 'human') {
   stage("start test job") 
    {
       echo "START JOB"
       def currentBuild
       catchError {
       currentBuild = build job: 'AllureTest/allure', parameters: [string(name: 'testList', value: '*')]
          echo currentBuild
       }
       echo currentBuild
       echo "END JOB"
        def testResultAction = currentBuild.rawBuild.getAction(AbstractTestResultAction.class)
        def failedTests = testResultAction.getFailedTests()
        def failedTestsString = "```"
        for(CaseResult cr : failedTests) {
            failedTestsString = failedTestsString + "${cr.getFullDisplayName()}:\n${cr.getErrorDetails()}\n\n"
         }
        echo failedTestsString
    }
            
}


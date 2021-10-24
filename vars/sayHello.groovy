#!/usr/bin/env groovy

def call(String name = 'human') {
   stage("start test job") 
    {
        build job: 'AllureTest/allure', parameters: [string(name: 'testList', value: '*')]
    }
}


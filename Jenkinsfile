pipeline {
   agent any

   tools {
      maven 'maven-3.8.4'
      jdk 'JDK 17'
   }

   stages {
      stage('Checkout') {
         steps{
            checkout scm
         }
      }
      stage('Build') {
        steps {
            sh 'mvn clean verify'
        }
      }
   }
}
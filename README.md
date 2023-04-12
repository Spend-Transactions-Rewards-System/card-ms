<a name="readme-top"></a>



<!-- PROJECT LOGO -->
<br />
<div align="center">
<h3 align="center">ITSA G1 T3 Project B AY2022/23 Semester 2</h3>

  <p align="center">
    Upload Microservice
    <br />
    <a href="https://cs301g1t3.stoplight.io/docs/card-ms/"><strong>Explore the docs »</strong></a>
    <br />
    <br />
    <a href="https://www.itsag1t3.com">View Demo</a>
  </p>
</div>


<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li><a href="#acknowledgments">Acknowledgments</a></li>
  </ol>
</details>


<!-- ABOUT THE PROJECT -->
## About The Microservice
Reward programs are becoming a popular marketing tool for banks and credit card issuers to attract and retain customers. Our application provides customers with an efficient and user-friendly platform for managing their rewards. A large number of spend transactions can be processed daily in real-time and the application is able to accept these transactions via a file upload or API request to the tenant. Campaign management is available for tenants, where they can run customisable campaigns for specific card programs that encourage user spending, while at the same time reward customers. From a customer perspective, this enhances the perceived value of these card programs offered by the tenant, helping our affiliated banks to preserve brand loyalty and expand their market share.

The card microservice aims to provide functionalities to save and retrieve customer rewards.  


### Built With

* [![Spring Boot][Spring-Boot.com]][Spring-Boot-url]
* [![Spring Cloud AWS][Spring-Cloud-AWS-SQS.com]][Sping-Cloud-AWS-url]
* [![AWS SQS][AWS-SQS.com]][AWS-SQS-url]
* [![JDK][JDK.com]][JDK-url]
* [![MAVEN][MAVEN.com]][MAVEN-url]



<!-- GETTING STARTED -->
## Getting Started
### Prerequisites
1. Create SQS Queue with the name: CampaignToCard
2. Create SQS Queue with the name: CardToCampaign
3. Retrieve _**Access Key ID**_ and _**Secret Access Key**_ from your IAM user
4. Install [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
5. Install [Maven 3](https://maven.apache.org)


### Installation (Windows)
1. Clone the repo
   ```sh
   git clone https://github.com/cs301-itsa/project-2022-23t2-g1-t3-be-card-ms.git
   ```
2. Change directory into the repo
    ```sh
    cd project-2022-23t2-g1-t3-be-card-ms
    ```
3. Install project dependencies
   ```sh
   mvn clean install
   ```
4. Update application.properties
   ```sh
   aws.campaign.to.card.queue.url=<URL Link to the campaignToCard queue in AWS>
aws.card.to.campaign.queue.url=<URL Link to the cardToCampaign queue in AWS>
   ```   

5. Create an environment file and add your environment variables
   ```sh
   echo > ./.env
   ```
   Replace variables in curly braces with your own credentials
   ```txt
   LOG_LEVEL={Log level}
   ACCESS_KEY_ID={AWS Access Key ID}
   JPA_DDL_CONFIG={Create/Update/None}
   SECRET_ACCESS_KEY={AWS Secret Access Key}
   AWS_REGION={AWS Region}
   DB_MYSQL_URL={DB Connection String}
   DB_MYSQL_USERNAME={DB User Name}
   DB_MYSQL_PASSWORD={DB Password}
   
   ```
6. Run the microservice (DEV MODE) - Uses H2 Database

   ```sh
   mvn spring-boot:run -P dev
   ```

7. Run the microservice (PROD MODE) - Uses MYSQL Database
   ```sh
   mvn spring-boot:run 
   ```
<!-- ACKNOWLEDGMENTS -->
## Acknowledgments

### Team Members
* Sean Tan
* Joshua Zhang
* Tan Gi Han
* Stanford
* Hafil
* Dino
* Gan Shao Hong

### Project Advisor/Mentor
* [Professor Ouh Eng Lieh](https://www.linkedin.com/in/eng-lieh-ouh/?originalSubdomain=sg)

<p align="right">(<a href="#readme-top">back to top</a>)</p>

[Sping-Cloud-AWS-url]: https://docs.awspring.io/spring-cloud-aws/docs/3.0.0-SNAPSHOT/reference/html/index.html
[Spring-Cloud-AWS-SQS.com]: https://img.shields.io/badge/Spring--Cloud--AWS-SQS-green
[Spring-Boot-url]: https://docs.spring.io/spring-boot/docs/current/reference/html/
[Spring-Boot.com]: https://img.shields.io/badge/SpringBoot-3.0.2-brightgreen
[AWS-SQS-url]: https://aws.amazon.com/sqs/
[AWS-SQS.com]: https://img.shields.io/badge/AWS-SQS-orange
[MAVEN-url]: https://maven.apache.org/
[MAVEN.com]: https://img.shields.io/badge/MAVEN-3.9-red
[JDK-url]: https://jdk.java.net/17/
[JDK.com]: https://img.shields.io/badge/JAVA--JDK-17-orange

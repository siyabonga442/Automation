# DemoWebShop Selenium Automation Project for Flash Assignment Quality Assurance

## Project Overview
Automated testing for DemoWebShop website using Selenium WebDriver, Java, and Maven.

## Prerequisites
- Java 11+
- Maven
- Chrome Browser

## Setup Instructions
1. Clone the repository
2. Install dependencies: `mvn clean install`
3. Run tests: `mvn test`

## Observed Bugs and Vulnerabilities:
    1. Insufficient Input Validation
       - Registration allows weak passwords
       - No complex password requirements
       - Minimal email format validation

    2. Potential Security Vulnerabilities
       - Limited protection against basic SQL injection attempts
       - No CAPTCHA or advanced bot protection
       - Predictable user registration process

    3. Cart and Pricing Vulnerabilities
       - Potential client-side price manipulation risks
       - Lack of server-side quantity validation
       - No strict input sanitization for numeric fields

    4. Performance and Load Issues
       - Potentially slow page loads during peak traffic
       - Limited error handling for concurrent user actions

    5. User Experience Issues
       - Inconsistent error messaging
       - Minimal guidance for password creation
       - No multi-factor authentication

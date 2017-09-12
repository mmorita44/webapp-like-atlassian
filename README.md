Web application like Atlassian
=========================

[![licence badge]][licence]

This is an example of a web application like Atlassian products.

# Functions

  * Java EE (Beans, JSP, Servlet)
  * Spring DI
  * Jersey
  * ActiveObjects
  * Velocity

# Maven tomcat7:run in debug mode with IntelliJ IDEA

In Intellij IDEA, in the Maven Projects tab, dig down to the tomcat7:run goal and then right click and select Debug like so:

![](https://i.stack.imgur.com/gCajp.png)

<cite>[debugging - How to run tomcat7-maven-plugin in debug mode with IntelliJ IDEA - Stack Overflow](https://stackoverflow.com/questions/40703688/how-to-run-tomcat7-maven-plugin-in-debug-mode-with-intellij-idea)</cite>

# Maven test in debug mode with IntelliJ IDEA

Open your run/debug configuration, in "Runner" tab, add `-DforkMode=never`

![](https://i.stack.imgur.com/1eoAs.png)

<cite>[testing - In IntelliJ, how do i debug a maven test goal? - Stack Overflow](https://stackoverflow.com/questions/3784781/in-intellij-how-do-i-debug-a-maven-test-goal)</cite>

[licence]: <LICENSE>
[licence badge]: http://img.shields.io/badge/license-MIT-blue.svg?style=flat
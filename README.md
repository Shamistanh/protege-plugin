# protege-summarizer-plugin

This repository has been developed on [protege-plugin-example](https://github.com/protegeproject/protege-plugin-examples.git), and has implemented additional functionalities.
This repository is a simple protege plugin for the Protege Desktop ontology editor (*versions 5.0.0 and higher*).  The Maven POM file in the top-level directory demonstrates one possible method for packaging plug-in code into the required OSGi bundle format using the [Maven Bundle Plugin](http://felix.apache.org/site/apache-felix-maven-bundle-plugin-bnd.html).

#### Prerequisites

To build and run the examples, you must have the following items installed:

+ Apache's [Maven](http://maven.apache.org/index.html).
+ A tool for checking out a [Git](http://git-scm.com/) repository.
+ A Protege distribution (5.0.0 or higher).  The Protege 5.2.0 release is [available](http://protege.stanford.edu/products.php#desktop-protege) from the main Protege website. 

#### Build and install example plug-ins

1. Get a copy of the example code:

        git clone https://github.com/Shamistanh/protege-plugin.git
    
2. Change into the protege-plugin-summarizer directory.

3. Type mvn clean package.  On build completion, the "target" directory will contain a protege.plugin.examples-${version}.jar file.

4. Copy the JAR file from the target directory to the "plugins" subdirectory of your Protege distribution.
 
## Plugin features

#### How to Open Ontologies
1. You can open existing owl file.
+ From 'File' Tab, click 'open' and find ontology from your local machine
+ Open owl file and move to Summarizer Tab

2. You can fetch an ontology from internet
+ Type path of ontology. We have used famous [pizza ontology](https://protege.stanford.edu/ontologies/pizza/pizza.owl) for testing
+ Press on 'Fetch' button, and that is all

#### What you can do with the ontology

1. How To show elements
+ Click to 'Show Figures' tab.
+ Answer to question opened window

2. How to show relations
+ Click 'Show Relations' button

#### Changing Working screen

1. Press on 'Maximize' button, you will see bigger screen
2. Press on 'Minimize' screen to close that screen

#### Notes:
+ 'Clear' button what you have drawn before.
+ 'Exit' will directly close application.

<em>Summarizer tab in menu bar of application is under development, We are planning to add there summary and ontology editing functionalities</em>
#Installing Spark

###Pre-requisite: You have [JAVA 7.x](http://www.oracle.com/technetwork/java/javase/downloads/jdk7-downloads-1880260.html) installed.

##Installing on Windows

### Part-1 - Setting up Ubuntu on Windows
It is recommended that you setup virtualized Ubuntu Linux.
Click [here](http://blog.en.uptodown.com/virtualize-ubuntu-14-04-windows-using-virtualbox)
for step-by-step instruction to setup Ubuntu on Windows.

*   Download and install latest version of VirtualBox
(Oracle’s free and open source hardware virtualization software)
*   Download the latest stable version of Ubuntu
*   Create Ubuntu Virtual Machine


### Part-2 - Installing Java
*   Downloading and Installing Java


    $ which javac
    $ sudo apt-get update
    $ sudo apt-get -y install openjdk-7-jdk

### Part-3 - Installing and Configuring Spark
*   Download and Installing Apache Spark
    * http://spark.apache.org/downloads.html
    * Choose a Spark release: 1.5.1
    * Choose a package type: Pre-built for Hadoop 2.6 and later
    * Choose a download type: Select Apache Mirror

    * When a page with list of mirrors appears, click on the suggested, topmost Apache Mirror site.
    In the Save File dialog, just confirm the suggested download location, the Downloads folder.
    Once the download completes, in your terminal, navigate to $HOME/Downloads folder
    (if that is the place where you downloaded Spark) and unpack the Spark bundle


    $ cd $HOME/Downloads
    $ tar -xvf spark-1.5.1-bin-hadoop2.6.tgz
    $ cd $HOME                                             
    $ mkdir –p bin/sparks                                  
    $ mv Downloads/spark-1.5.1-bin-hadoop2.6 bin/sparks    

    #create symbolic link. It's useful to change to a newer version of Spark.
    $ cd $HOME/bin
    $ ln -s spark-1.5.1-bin-hadoop2.6 Spark #create symbolic link using command ln -s LINK_TARGET LINK_NAME


### Part-4 - Testing Installation
*   Spark Shell


    $ cd $HOME/bin/Spark ## If you haven't setup symbolic link, you need to provide
    the complete path to the Spark Home directory
    $ ./bin/spark-shell       #spark-shell --master "local[*]"

Note: You may get an error related to sqlContext. If you do, create sqlContext explicitly
val sqlContext = new org.apache.spark.sql.SQLContext(sc);

##Installing on Mac

Follow Part-2 - Part 4.

##Installing other tools



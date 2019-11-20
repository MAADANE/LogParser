# LogParser
LogParser, est une application web basée sur le Framework Spring Boot.
Elle permet de traiter, trier et stocker les fichier Log Access d’une manière
automatique, afin de les analyser d’une manière plus simple.

 - **Batch** 
 - **Application web**
 
## Batch
   1. Récupération du projet et configuration
   

 -  **Récupération du projet**
 
 Créez un répertoire local pour y stocker votre application, ouvrez ensuite une invite de commandes puis accédez au répertoire déjà crée et clonez le projet.
Vous pouvez utiliser la commande suivante :


     git clone --depth 1 https://gitlab.com/MAADANE/logparser.git -b batch 
 - **Préparation de la base de données**
 
Créez une base de données vide portant le nom "logaccess" en utilisant le script **"/resources/script/LogParser.sql"** .
Vous pouvez utiliser la commande suivante :


    mysql -h localhost -u username -ppassword < LogParser.sql

  Vérifiez que la base de données a bien été créée. 
  - **Configuration**

Configurer les paramètres nécessaires de la connexion avec la base de données dans le fichier de configuration **/resources/application.properties** : 
             

    spring.datasource.url=jdbc:mysql://nom_hote:port/logaccess
    spring.datasource.username=username
    spring.datasource.password=password
**exemple :**

     spring.datasource.url=jdbc:mysql://localhost:3306/logaccess
     spring.datasource.username=root
     spring.datasource.password=
  2.Build
  
Accédez au répertoire du projet terminé pour générer le fichier **JAR** en utilisant Maven .
Vous pouvez utiliser la commande suivante :


     mvn clean install
Ceci génère le fichier jar de l'application: **LogParsrer-0.0.1-SNAPSHOT.jar** dans le sous-dossier **/target**.

 3.Exploitation 

 - **Préparation des fichiers de log**
 
Préparez les fichiers log que vous voulez traiter dans un répertoire.
 
 - **Exécution**
 
Accédez au sous-dossier **/target** du répertoire du projet, puis passez le chemin d'accès du répertoire contenant les  fichiers log en paramètre lors de l'exécution du JAR .
Vous pouvez utiliser la commande suivante :


     java -jar LogParser-0.0.1-SNAPSHOT.jar "chemin_répertoire_logs"
     
 **exemple:**
 

     java -jar LogParser-0.0.1-SNAPSHOT.jar "C:\Users\sqli\Desktop\Logs"

   
> **Note:** Toute ligne ayant un problème sera tracée dans un fichier de logs en mentionnant l'exception levée lors du parsing.
> Pour visualisez les fichiers de logs, accédez au sous-dossier **/logs**
> Pour changer l'emplacement des fichier logs, il suffit d'éditer le fichier **/resources/logback-spring.xml** :
> `  <property name="LOGS" value="chemin_nouveau_emplacement" />`
> **exemple**:
> ` <property name="LOGS" value="./logs" />`

## Application web 

   1. Récupération du projet et configuration
   - **Récupération du projet**

Créez un répertoire local pour y stocker votre application, ouvrez ensuite une invite de commandes puis accédez au répertoire déjà crée et clonez le projet.
Vous pouvez utiliser la commande suivante :


     git clone  --depth 1  https://gitlab.com/MAADANE/logparser.git
 - **Configuration**
  
Configurer les paramètres nécessaires de la connexion avec la base de données dans le fichier de configuration **/resources/application.properties** : 
             

    spring.datasource.url=jdbc:mysql://nom_hote:port/logaccess
    spring.datasource.username=username
    spring.datasource.password=password
**exemple :**

     spring.datasource.url=jdbc:mysql://localhost:3306/logaccess
     spring.datasource.username=root
     spring.datasource.password=


   2. Build

Accédez au répertoire du projet terminé pour générer le fichier **WAR** en utilisant Maven.
Vous pouvez utiliser la commande suivante :


     mvn clean install
Ceci génère le fichier jar de l'application: **LogParsrer-0.0.1-SNAPSHOT.war** dans le sous-dossier **/target**

Lorsque l'application web a été créée, démarrez l'application web à l'aide de Maven.
 Vous pouvez utiliser la commande suivante :


     mvn spring-boot:run

Testez l'application web en y accédant localement via un navigateur web. 
Vous pouvez utiliser la commande suivante :

     start http://localhost:9090
     
> **Note:** Vous pouvez changer le numéro de port dans le fichier de configuration **/resources/application.properties**:
>            `server.port=9090
`


> 
# SopiaTech Eats-Team-23-24 

## EQUIPE F : UberTech

Pour la réalisation du projet UberTech réalisé dans le cadre du cours de Conception Logiciel à Polytech Nice Sophia, nous avons créer un groupe de 4 élèves constitué plus précisément de : 
- AZIKI Tarik (Software Architect)
- MAUROIS Quentin (Product Owner)
- FROMENT Lorenzo (Quality Assurance Engineer)
- BEUREL Simon (OPS)

## Documents

### Rapport initial : 
Ce rapport est le premier rendu du cours de Conception Logiciel dans lequel nous avons du réaliser différents diagrammes pour modéliser le projet UberTech en se basant sur les différentes spécifications données : 

https://docs.google.com/document/d/1D0iN9_z_zfN1CFQoi0CdzapFW7r7f5JIiacwII47E-M/edit?usp=sharing

### Rapport final : 
Ce rapport à été réalisé à la fin du projet de Conception Logiciel. Il contient différentes informations qui permettent de mieux comprendre le projet en profondeur, nos différents choix (Patterns etc....) ainsi que notre Auto-Evaluation.



## Principaux UC traités : 
- Passer une commande
  - Add a product to the customer's pendingOrder #3 -> **As a Customer, I want add a product to my pendingOrder so that store it and valid my pendingOrder later**
  - Retrieve Restaurant Hours #5
  - Choosing a restaurant #10
  - Pay for an Order #4
- Avoir accès à l'historique
  - Checking history #9
  - Customer gest acces to their history #31 -> **As a customer, I want to check my history so that I can see all my previous orders**
- Valider la livraison
  - Driver Confirmation of Delivery #12
  - Customer Confirmation of Delivery #13 -> **As a customer, I want to confirm the delivery of my order so that the order can be marked as "delivered" in the system, and I can provide feedback on the delivery experience.**
- Signaliser un livreur en retard
  - Report a late user by Delivery Personnel #36 -> **As a delivery person, I want to report users who are late for their orders, so that I can ensure effective monitoring and management of deliveries.**
  - Customer get bannPoints back #43
- Ajouter/Supprimer un restaurant
  - Add restaurant to the system #2 -> **As a Campus Administrator, I want add restaurant to the system's list so that any customer can order to this restaurant**
  - Delete a Restaurant #33
- Ajouter un livreur
  - Add a delivery person to the system #15 -> **As a campus administrator, I want to add a delivery person so that orders can be delivered to customers**
- Parcourir les menus
  - Retrieve Restaurant Hours #5
  - Choosing a restaurant #10 -> **As a customer, I want to choose a restaurant so that I can command product from their menu.**
- Consulter les statistiques
  - Customer statistics #62 -> **As a customer, I want to know my statistics so that I can make decisions depending on my wallet**
  - Restaurant statistics #64
  - Delivery person statistics #74
- Créer une commande groupée
   - Group orders #11 -> **As a Customer I want create a GroupOrder so that other Customers can join my GroupOrder**
- Etablir le paiement
  - Pay for an Order #4 -> **As a Customer, I want to be able to pay for my order so that the restaurant can prepare my order.**
- Obtenir une ristourne/réduction
  - Different price according to user type #42 -> **As a customer, I want to see different prices based on my user type (e.g., student, faculty, staff, external) so that I can make informed decisions about my purchases.**
  - Restaurant discount #44
  - Discount V1 #51
- Accepter/Refuser/Confirmer une commande
  - Order receipt confirmation by the restaurant employee #14 -> **As a restaurant employee, I want to confirm the receipt of the customer's order so that I can start preparing it**
  - Restaurant's notification when a new order is upcoming #29
- Notifier le livreur
   - DeliveryPerson's notification #1 -> **As a DeliveryPerson, I want to be notified when an order is ready so that I can pick it up**
- Gestion du restaurant
  - Edit information about Restaurant #30 
  - Addition of New Products by the Restaurant #34
  - Removing Products by the Restaurant #49
  - Modifying Products from the menu by the Restaurant #50
  - Production's capacity slots #54 -> **As a Restaurant, I wantto be able got a production's capacityso thatI dont have too much work**
## Comment lancer le projet ? 
Pour pouvoir lancer le projet, tout d'abord vous devez avoir en prérequis Maven, Java et Git sur votre machine, ensuite vous allez devoir réaliser ces commandes :
- ```git clone https://github.com/PNS-Conception/ste-23-24-equipe-f-ubertech.git```
- ```cd ste-23-24-equipe-f-ubertech```
- ```mvn clean package``` (pour build le projet)
- ```mvn clean test``` (pour lancer les différents tests)
## Structuration du projet : 
Notre projet se compose de plusieurs dossiers bien spécifiques où chacuns possèdent un but bien spécifique. Tout d'abord, à la racine du projet vous trouverez le dossier ```src``` qui correspond au dossier contentenant tout le code réalisé durant ce projet. Vous pourrez également trouver à la racine du projet un dossier ```.github``` qui est très important car ce dossier contient notamment les différentes règles implémentées pour l'exécution de Github Action dans le fichier ```maven.yml``` présent dans le dossier ```.github/workflows```.

A l'intérieur du dossier ```src```, vous trouverez deux dossiers très importants, le dossier ```test``` et le dossier ```main``` : 
### Dossier main : 
Ce dossier va contenir les différents fichiers .java qui correspondents aux Classes/Interfaces implémentées pour la réalisation de ce projet. Tous ces fichiers sont intéressants pour comprendre le comportement de chaque Objet se trouvent dans le package ```sophiatech```. Vous trouverez ainsi dans ce dossier, différents fichiers comme Customer.java, DeliveryPerson.java etc....
### Dossier test : 
Ce dossier va contenir une des partie les plus importantes de ce projet car c'est celui qui contiendra les différents tests effectués pour vérifier le bon comportement de nos classes. Tout d'abord, à l'intérieur de ce dossier, on trouvera deux sous-dossiers ```java``` et ```ressources```. 

Le dossier ```ressources``` est le dossier qui va contenir les différents fichiers .feature. Ces fichiers correspondent aux différents tests Gherkin réalisés pour l'exécution de tests Cucumber. Ces fichiers, implémentés grâce au langage Gherkin suivent un format bien précis de tests avec un format "Given..... When..... Then....."

Le dossier ```java``` lui, contiendra l'implémentation de ces tests Gherkin mais cette fois-ci en langage Java. Par exemple, dans le dossiers ```ressources``` on peut trouver un fichier adddeliveryperson.feature, et donc par conséquent dans le dossier ```java``` on va pouvoir trouver un fichier AddDeliveryPerson.java, qui correspond au fichier Java implémentant le test décrit dans le fichier Gherkin. Tous ces fichiers Java seront ceux qui seront exécutés lors d'un appel Maven ```mvn clean test```

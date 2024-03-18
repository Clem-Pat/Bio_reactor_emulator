## Le Bioréacteur
### Simulateur Numérique de bioréacteur en version distribuée

L'objectif principal de ce projet est de développer  un émulateur de bioréacteur en version distribuée, permettant son exécution sur plusieurs machines. Cette application sera conçue en Java et intégrera différents motifs de conception pour assurer sa robustesse et sa flexibilité. Plus spécifiquement, l'émulateur sera chargé de reproduire les résultats d'une expérience réalisée dans un fermenteur, en exploitant les données extraites d'un fichier Excel. Ces données comprendront des paramètres tels que la température, l'oxygénation et l'acidité, entre autres.
À mesure que l'expérience est simulée à travers les données fournies, un client reçoit en temps réel l'actualisation de toutes les variables et peut encore choisir un instant quelconque pour retrouver l'état du système à ce stade.

Le cahier des charges impose plusieurs motifs de conception à mettre en place au cours de ce projet. En effet, l'architecture du projet est libre, mais compte tenu des objectifs fixés il est exigé que le code source du projet qui sera rendu à la fin intègre : le serveur TCP concurrent, le sujet-observateur de l’API Java, le pattern Stratégie, le pattern composant-composite et enfin le pattern État. De plus, notre application doit également etre capable de lire des données à partir du fichier Excel, organiser des données pour les rendre disponibles dans un serveur, envoyer des données à tout client connecté sur le serveur, visualiser l'évolution des données dans les interfaces utilisateur des clients et enfin envoyer des valeurs de commandes lorsque nécessaire.

### Exécution
Après avoir télechargé le projet, exécutez Bio_reactor_emulator/out/production/reactor/bioreactor/MainBioreactor.class puis Bio_reactor_emulator/out/production/reactor/client/MainClient.java 

### Architecture
[Simplified Architecture](https://enstabretagne-my.sharepoint.com/:i:/g/personal/victor_queiroz_ensta-bretagne_org/EYz3aq8TPSJMu6IilJv9rIMBZR3Pk8VQ2UkMeWuMX0f8nQ?e=Vdpe5u)

Notre projet est structuré en deux modules distincts : un module client et un module bioréacteur\ref{appendix:raw}.. Ces deux modules sont conçus de manière indépendante pour assurer une interaction claire et bien définie avec les autres composants du système. Chacun de ces modules est doté d'une interface spécifique, permettant une interaction simple avec l'utilisateur. La communication entre ces deux modules est gérée par un serveur TCP, agissant comme un médiateur pour les échanges de données au sein de l'application.

[Complete Architecture](https://enstabretagne-my.sharepoint.com/:i:/g/personal/victor_queiroz_ensta-bretagne_org/EXojc2LkLolFiJ3FAP_43tQBTGpfWETSFKZh-Nl6q7f1oQ?e=yTXuhr)

#### Representation en Diagramme de Séquence

Pour ilustrer ce que vient d'être décrit un diagramme a été conçu Il est simplifié tant en termes de la communication, qu'est réalisé en continu, comme en enlevant les *handshakes* qui la lancent.

[Diagramme Sequentiel Simple](https://enstabretagne-my.sharepoint.com/:i:/g/personal/victor_queiroz_ensta-bretagne_org/EXa6kITP1N1Gp6sh2NhQlk8BEeXAKIIp_FYSuyhT0qPS6A?e=gCgxRv)

#### Points à améliorer

Par dessin, le programme présente des problèmes en quelques cas:
1. Demande d'un paramètre pas encore simulé par le Client. C'est impossible d'un point de vue logique donner au client les informations de ce qui n'a pas encore été simulé, cependant une message d'erreur serait préférable a la faillite du programme.
2. Lancement du client avant le serveur. En ce cas on voit des paramètres impossibles.
3. La fin de l'expérience avant la fin du temps totale de simulation.

D'un autre côté, le programme est plutôt simple et plusieurs patrons de conception sont utilisés, l'amélioration est toujours possible mais la structure a été jugée satisfaisante.

### Auteurs

- [@teatoscan](https://www.github.com/teatoscan)
- [@Victor-Alessandro](https://github.com/Victor-Alessandro)
- [@Clem-Pat](https://www.github.com/Clem-Pat)

### JavaDoc 
https://clem-pat.github.io/Bio_reactor_emulator/

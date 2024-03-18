## Le Bioréacteur
### Jumeau Numérique de bioréacteur en version distribuée

Le cahier des charges impose plusieurs motifs de conception à mettre en place au cours de ce projet. En effet, l'architecture du projet est libre, mais compte tenu des objectifs fixés il est exigé que le code source du projet qui sera rendu à la fin intègre : le serveur TCP concurrent, le sujet-observateur de l’API Java, le pattern Stratégie, le pattern composant-composite et enfin le pattern État. De plus, notre application doit également etre capable de lire des données à partir du fichier Excel, organiser des données pour les rendre disponibles dans un serveur, envoyer des données à tout client connecté sur le serveur, visualiser l'évolution des données dans les interfaces utilisateur des clients et enfin envoyer des valeurs de commandes lorsque nécessaire.

### Exécution
Après avoir télechargé le projet, exécutez Bio_reactor_emulator/out/production/reactor/bioreactor/MainBioreactor.class puis Bio_reactor_emulator/out/production/reactor/client/MainClient.java 

### Architecture
[Simplified Architecture](https://enstabretagne-my.sharepoint.com/:i:/g/personal/victor_queiroz_ensta-bretagne_org/EYz3aq8TPSJMu6IilJv9rIMBZR3Pk8VQ2UkMeWuMX0f8nQ?e=Vdpe5u)
Notre projet est structuré en deux modules distincts : un module client et un module bioréacteur\ref{appendix:raw}.. Ces deux modules sont conçus de manière indépendante pour assurer une interaction claire et bien définie avec les autres composants du système. Chacun de ces modules est doté d'une interface spécifique, permettant une interaction simple avec l'utilisateur. La communication entre ces deux modules est gérée par un serveur TCP, agissant comme un médiateur pour les échanges de données au sein de l'application.

Pour comprendre comment le programme fonctionne, une description de ces étapes dans l'ordre est intéressante.

1. Le module bioreacteur comprend la classe \textit{MainBioreactor}, dont la méthode \textit{main} est la première à être exécutée lors du lancement du programme. Cette fonction initie deux fonctions principales \textit{Bioreacteur }et \textit{BioreacteurGUI}. En effet, cette fonction crée une instance de l'interface graphique du bioréacteur \textit{BioreactorGUI} en utilisant également un objet \textit{Bioreactor} pour initialiser l'interface graphique avec les données nécessaires.
   La classe \textit{BioreactorGUI} représente l'interface graphique du bioréacteur et est conçue de manière à être indépendante de la classe \textit{Bioreacteur}. Cette classe utilise en effet le pattern observateur-observable pour détecter les changements dans les données du bioréacteur et les refléter dynamiquement dans l'interface utilisateur. Ainsi, si une variable du bioréacteur est mise à jour, la méthode \textit{propertyChange} de la classe \textit{BioreactorGUI} est déclenchée pour actualiser les données affichées à l'utilisateur, grâce à l'argument evt qui permet l'accès à la liste des variables.
   
3. Le package \textit{variableManager} est une composante essentielle de notre application, regroupant les classes nécessaires à la gestion des variables du bioréacteur. Au cœur de ce package se trouve l'interface \textit{IVariable}, qui définit les méthodes communes à toutes les variables, à savoir \textit{getValue} pour obtenir la valeur de la variable et \textit{getTime} pour obtenir le temps associé à cette valeur. Cette interface sert de contrat pour toutes les classes de variables. \\
4. PropertyChangeSupport a été largement utilisé notamment dans les fenêtres d'affichage. Ceci nous permet d'utiliser le shéma de conception "Observateur-observable". Cela répond à la nécessité de pouvoir ajouter des interfaces graphiques liées à un même client. D'autre part, ce principe est fondamental lors de la simulation du temps qui s'écoule dans le bioréacteur entre chaque mesure.
5. Un autre point important est l'initialisation d'un element runnable dans le client et dans le bioréacteur pour permettre au code de reproduire justement l'écoulement du temps précédemment mentionné.
6. Pour les interfaces graphiques du client et du bioréacteur le patron de composite est employé, cette choix est basé dans le fait que la structure en arbre pour les elements qui compesent les interfaces visuelles est naturelle. On peu comprendre le GUI comme un carré qu'on subdivise en plusieur carrées successivement avec les Strings despcriptives et/ou champs à remplir dans la fin. Même si chaque portion est lègerement different, des abstractions permettent la conception en arbre.
7. À la fin, pour le client, les Strings sont *parsed* pour retrouver les valeurs proches des valeurs doubles envoyés. Le client pourrait demander les données de différents façons, dont deux sont implémentés. Une en continu et l'autre sous demande directe d'un moment spécifique. Le patron stratégie aurait été plus utile si le programme changeait selon des contextes, mais les deux façons ont été considérées importantes et le contexte enlevé. Néanmoins, l'architecture choisie permet qu'en ajoutant une interface pour le contexte et les méthodes/stratégies la pièce soit enlargi pour accepter plusieurs stratégies.

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

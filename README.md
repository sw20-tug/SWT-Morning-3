# SWT: Morning 3 - Note
 
## Development

Before you can build this project, you must install and configure the following dependencies on your machine:

[Node.js][]: We use Node to run a development web server and build the project.
   Depending on your system, you can install Node either from source or as a pre-packaged bundle.

After installing Node, you should be able to run the following command in the fronted folder to install development tools.
You will only need to run this command when dependencies change in [package.json](frontend/package.json).

    npm install

<!--We use npm scripts and [Webpack][] as our build system.

Run the following commands in two separate terminals to create a blissful development experience where your browser
auto-refreshes when files change on your hard drive.

    ./mvnw
    npm start
-->
To start the backend either use the import event in IntelliJ or use the following command in the backend folder:

    mvn package

To start the frontend execute the following command in the frontend folder:

    npm run serve

Npm is also used to manage CSS and JavaScript dependencies used in this application. You can upgrade dependencies by
specifying a newer version in [package.json](frontend/package.json). You can also run `npm update` and `npm install` to manage dependencies.
Add the `help` flag on any command to see how you can use it. For example, `npm help update`.

The `npm run` command will list all of the scripts available to run for this project.

### Database and Mockobjects
If there is no database connection for whatever reason use json mock objects:

    npm install json-server
    npx json-server data-mock.json --port=8082
    

## Testing

### Integration Tests

For the integration tests please run:

    npm run test:e2e

[node.js]: https://nodejs.org/
[webpack]: https://webpack.github.io/
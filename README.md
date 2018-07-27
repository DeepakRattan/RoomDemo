# RoomDemo
This project demonstrates the use of one of the Architectural compenent i.e Room library that acts as an abstract layer for SQLite database.

Here I have used one activity named MainActivity where i have performed CRUD operations using ROOM library.

<b>There are 3 major components in Room</b>

 <b>1. Entity : </b>
It’s a model class annotated with @Entity where all the variable will becomes column name for the table and name of the model class becomes name of the table.
    
    tableName attribute is used to define the name of the table
    
    Every entity class must have at-least one Primary Key field, annotated with @PrimaryKey
    
    Fields in entity class can be annotated with @ColumnInfo(name = “name_of_column”) annotation to give specific column names

<b>2. Dao(Data Access Object:</b> 
DAO : Data Access Object is either be an interface or an abstract class annotated with @Doa annotation, containing all the methods to define the operations to be performed on data. The methods can be annotated with

    @Query to retrieve data from database
    @Insert to insert data into database
    @Delete to delete data from database
    @Update to update data in database


<b>3. Database: </b>

Database is a container for tables. An abstract class annotated with @Database annotation is used to create a database with given name along with database version.

    version = intValueForDBVersion is used to define the database version
    entities = {EntityClassOne.class, ....} is used to define list of entities for database
    
   Here i have stored username and password in SQlite through Room library.
   
   Use the following dependencies for Room:
   
  <b>  // Room dependencies</b>
  
    implementation 'android.arch.persistence.room:runtime:1.0.0'
    annotationProcessor 'android.arch.persistence.room:compiler:1.0.0'
    
   
   <b>1. User class is responsible for Table creation.</b>
   
   <b>2. In UserDao i have specified all the CRUD operations to be performed on database</b>
   
   <b>3. UserDB is reperesenting the database</b>
    
    
    
    Room does not allow code execution on Main thread. Therefor i have used AsyncTask in MainActivity to perform CRUD operations.
    
    
    
    

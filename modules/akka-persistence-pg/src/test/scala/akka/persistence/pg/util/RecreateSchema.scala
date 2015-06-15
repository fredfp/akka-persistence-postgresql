package akka.persistence.pg.util

trait RecreateSchema {

  def schemaName: String

  import akka.persistence.pg.PgPostgresDriver.api._

  val recreateSchema: DBIOAction[Unit, NoStream, _] = DBIO.seq(
    SimpleDBIO(_.connection.createStatement.execute(s"""drop schema if exists "$schemaName" cascade;""")),
    SimpleDBIO(_.connection.createStatement.execute(s"""create schema "$schemaName";"""))
  )

}
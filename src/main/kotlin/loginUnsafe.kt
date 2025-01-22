import java.sql.Connection
import java.sql.DriverManager

fun loginUnsafe(username: String, password: String): Boolean {
    val conn: Connection = DriverManager.getConnection("jdbc:sqlite:users.db")
    val statement = conn.createStatement()
    val query = "SELECT * FROM users WHERE username = '$username' AND password = '$password'"
    val resultSet = statement.executeQuery(query)

    return resultSet.next()
}

fun loginSafe(username: String, password: String): Boolean {
    val conn: Connection = DriverManager.getConnection("jdbc:sqlite:users.db")
    val query = "SELECT * FROM users WHERE username = ? AND password = ?"
    val preparedStatement = conn.prepareStatement(query)
    preparedStatement.setString(1, username)
    preparedStatement.setString(2, password)

    val resultSet = preparedStatement.executeQuery()
    return resultSet.next()
}

fun main() {
    println("Podaj login: ")
    val username = readln()
    println("Podaj hasło: ")
    val password = readln()
    if (loginUnsafe(username, password)) {
        println("Zalogowano!")
    } else {
        println("Błędny login lub hasło!")
    }
}

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
    val username = readLine()!!
    println("Podaj hasło: ")
    val password = readLine()!!

    if (loginSafe(username, password)) {
        println("Zalogowano (BEZPIECZNIE)!")
    } else {
        println("Błędny login lub hasło!")
    }
}

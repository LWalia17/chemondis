
object Challenge extends App {

  import scala.util.Random
  import java.time.{LocalDateTime, Instant, ZoneId}
  import java.sql.{Connection, DriverManager, ResultSet}
  import java.sql.{SQLException, Statement}

  val categories = List("category1", "category2", "category3")
  val actions = List("action1", "action2", "", "action3")
  val labels = List("label1", "label2", "label3", null)
  val searchTerms = List("term1", "term2", "term3")
  val ips = List("192.168.1.1", "192.168.1.2", "192.168.1.3")
  val userIds = (1 to 100).map(_.toString)
  val firstNames = List("Emma", "Liam", "Ava", null, "Noah", "Sophia")
  val lastNames = List("Smith", "Johnson", "Brown", "Davis", "Garcia")
  val browsers = List("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.82 Safari/537.36 (Google Chrome)", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.15; rv:83.0) Gecko/20100101 Firefox/83.0 (Mozilla Firefox)", "Mozilla/5.0 (Windows NT 10.0; Trident/7.0; rv:11.0) like Gecko (Microsoft Internet Explorer/Edge)")

  /**
   * Generates Strings like
   * 192.168.1.3;2023-03-05T15:03:28;Liam Brown;22;category2;action2;label2;term2;Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.82 Safari/537.36 (Google Chrome)
   */
  def generateRandomData: String = {
    val random = new Random()
    // random date in the next seven days from now
    val date = LocalDateTime.ofInstant(
      Instant.ofEpochSecond(Instant.now().getEpochSecond + Random.nextInt(7 * 86400)
      ), ZoneId.systemDefault())
    val ip = ips(random.nextInt(ips.length))
    val userId = userIds(random.nextInt(userIds.length))
    val category = categories(random.nextInt(categories.length))
    val action = actions(random.nextInt(actions.length))
    val label = labels(random.nextInt(labels.length))
    val searchTerm = searchTerms(random.nextInt(searchTerms.length))
    val firstName = firstNames(random.nextInt(firstNames.length))
    val lastName = lastNames(random.nextInt(lastNames.length))
    val browser = browsers(random.nextInt(browsers.length))
    s"$ip;$date;$firstName $lastName;$userId;$category;$action;$label;$searchTerm;$browser"
  }
  
  
  def saveToDatabase(data: Iterator[String]) = {
  try {
   val statement = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE)

   for(i <- data){
    var prep = conn.prepareStatement("INSERT INTO public.user_login values (?,  ? , ? )  ")
    prep.setString(1, i.split(';')(0))
    prep.setString(2,i.split(';')(10))
    prep.setInt(3, i.split(';')(3).toInt)

    prep.executeUpdate

    prep = conn.prepareStatement("INSERT INTO public.activity values (?,  ?, ?, ?, ?, ? )  ")
    prep.setString(1,i.split(';')(4))
    prep.setString(2, i.split(';')(6))
    prep.setString(3, i.split(';')(5))
    prep.setString(4, i.split(';')(7))
    prep.setString(5, i.split(';')(1))
    prep.setInt(6, i.split(';')(3).toInt)
    prep.executeUpdate

    //  CONFLICT DO NOTHING to ensure PK contraints holds but skips the row 
    prep = conn.prepareStatement("INSERT INTO public.usert1 values (?,  ? , ?)  ON CONFLICT DO NOTHING ")
    prep.setInt(1,i.split(';')(3).toInt)
    prep.setString(2,i.split(';')(2))
    prep.setString(3, i.split(';')(0))
    prep.executeUpdate

   }

} 
  catch  
   case ex : SQLException =>  {if ex.getMessage.contains("duplicate") then println("Duplicate records cannot be inserted for User ID")}
   finally {
     conn.close()
    }
}
  
 
  

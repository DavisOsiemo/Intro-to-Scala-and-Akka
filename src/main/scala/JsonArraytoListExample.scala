////import akka.http.impl.util.Util
//import akka.http.impl.util.Util
//import org.json._
//import org.json.JSONObject
//import org.json.JSONTokener
//
//import scala.collection.JavaConverters._
//
//object JsonArraytoListExample {
//  def decodeJSONArray(jSONString: String) = {
//    val ja = new JSONArray(jSONString)
//    new Array[Int](ja.length).indices.map (i => ja.get(i)).toArray
//  }
//  def getJSONKeys (jSONString: String) = {
//    val jo = new JSONTokener(jSONString).nextValue().asInstanceOf[JSONObject]
//    jo.keys.map(_.toString).toList
//  }
//
//
//  def getJSONParams (names: List[String], jsonString: String) = {
//    val jo = new JSONTokener(jsonString).nextValue().asInstanceOf[JSONObject];
//    names.indices.map(i => jo.getString(names.apply(i)))
//  }
//  def encodeJSONSeq (s: Seq[_]) = encodeJSONArray(s.toArray)
//  def encodeJSONArray(a: Array[_]) = new JSONArray(a)
//  def createJSONString (keys: Array[String], vals: Array[_]) = createJSONObject (keys, vals).toString
//  def createJSObject(keys: Array [String], vals: Array[_]) = {
//    val jo = new JSONObject
//    keys.indices.foreach(i => jo.put(keys.apply(i), vals.apply(i)))
//    jo
//  }
//  def jsonStringToXML(s: String) = try scala.xml.XML.loadString ("<JSON>" + org.json.XML.toString(new
//  JSONObject(s)) + "</JSON>") catch {
//    case e: Any =>
//      if (Util.debug) e.printStackTrace
//      <error> {e.getMessage}</error>
//  }
//  trait JSONFormatted {
//    val keys: Array[String]
//    val vals: Array[Any]
//    override def toString = createJSONString(keys, vals)
//  }
//}

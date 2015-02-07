package panoptes_storm

import spray.json._
import DefaultJsonProtocol._
import backtype.storm.tuple.{Values,Fields}
import backtype.storm.spout.Scheme

case class Classification(id:String, annotations:Array[Map[String,String]],
  created_at: String, updated_at:String, completed:Boolean, metadata:Map[String,String],
  user_id:String, set_member_subject_ids:Array[String], project_id:String,
  workflow_id:String)

object ClassificationProtocol extends DefaultJsonProtocol {
  implicit val classificationFormat = jsonFormat10(Classification)

}

import ClassificationProtocol._

class JSONScheme extends Scheme {
  def deserialize(bytes: Array[Byte]): java.util.List[Object] = {
    val classification = (new String(bytes)).parseJson.convertTo[Classification]
    val list = new java.util.ArrayList[Object]()
    list.add(classification.id)
    list.add(classification.annotations)
    list.add(classification.created_at)
    list.add(classification.updated_at)
    list.add(classification.metadata)
    list.add(classification.user_id)
    list.add(classification.set_member_subject_ids)
    list.add(classification.project_id)
    list.add(classification.workflow_id)
    return list
  }

  def getOutputFields(): Fields = new Fields("id", "annotations", "created_at", "updated_at", "metadata", "user", "set_member_subjects", "project", "workflow")
}

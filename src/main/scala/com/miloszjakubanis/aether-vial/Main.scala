import java.util.concurrent.TimeUnit
import scala.concurrent.ExecutionContext
import scala.util.{Failure, Success}
import scala.concurrent.Future
import scala.concurrent.Await
import scala.concurrent.duration.Duration
import com.miloszjakubanis.`aether-vial`.AbstractJob

given scala.concurrent.ExecutionContext = scala.concurrent.ExecutionContext.global
@main
def main =
  val job = AbstractJob[String, Int](e => Future{println("parsing int job");Integer.parseInt(e)})
  val job2 = AbstractJob[Int, Unit](e => Future{println("printing job");println(e)})

  val composition = job + job2

  val result = composition("111a")

  result.onComplete {
    case Success(e) => println("sucessful!")
    case Failure(e) => throw new RuntimeException("Error happened")
  }

  // job("111") 

  // val pipeline = new SimplePipeline()
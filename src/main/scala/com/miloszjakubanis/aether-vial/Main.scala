import java.util.concurrent.TimeUnit
import com.miloszjakubanis.`aether-vial`.{AbstractJob, PrintingJob, FileWritingJob}
import scala.concurrent.ExecutionContext
import scala.util.{Failure, Success}
import scala.concurrent.Future
import scala.concurrent.Await
import scala.concurrent.duration.Duration
import com.miloszjakubanis.`aether-vial`.given_ExecutionContext


private def convert(i: Int): Future[String] = Future(i.toString)
@main
def main =
  val job = AbstractJob[Int, String]
   (323)(convert)

  val z = job(e => println(e))
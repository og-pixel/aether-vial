import java.util.concurrent.TimeUnit
import com.miloszjakubanis.`aether-vial`.{
  AbstractJob,
  PrinterJob,
  JobComposition
}
import scala.concurrent.ExecutionContext
import scala.util.{Failure, Success}
import scala.concurrent.Future
import scala.concurrent.Await
import scala.concurrent.duration.Duration
import com.miloszjakubanis.`aether-vial`.AbstractJob
import com.miloszjakubanis.`aether-vial`.given_ExecutionContext

private def convert(i: Int): Future[String] = Future {
  println("starting  conversion")
  Thread.sleep(1000)
  i.toString
}

@main
def main = 
  val a = AbstractJob(2727)(convert)()
  val b = AbstractJob(1000)(convert)()

  val res = for {
    res1 <- a
    res2 <- b
  } yield s"$res1 ### $res2"

  res.foreach(println(_))
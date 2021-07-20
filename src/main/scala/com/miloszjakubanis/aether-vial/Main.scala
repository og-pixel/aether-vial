import java.util.concurrent.TimeUnit
import com.miloszjakubanis.`aether-vial`.{AbstractJob, PrintingJob, FileWritingJob}
import scala.concurrent.ExecutionContext
import scala.util.{Failure, Success}
import scala.concurrent.Future
import scala.concurrent.Await
import scala.concurrent.duration.Duration
import com.miloszjakubanis.`aether-vial`.given_ExecutionContext

// given scala.concurrent.ExecutionContext = scala.concurrent.ExecutionContext.global
@main
def main =
  val job = AbstractJob[Int, String]
   (323)(
   (i: Int) => Future {
     // Thread.sleep(1000)
     i.toString
   })

  val result = job()
  job.handleResults(println(_))

  val job2 = PrintingJob("Hello world")
  job2()


  val fileJob = FileWritingJob("Hello world from the concurrent application!")
  fileJob()
  
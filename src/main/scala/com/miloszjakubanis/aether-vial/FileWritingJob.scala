package com.miloszjakubanis.`aether-vial`
import scala.concurrent.{ExecutionContext, Future}
import java.nio.file.Files
import java.nio.file.Paths

class FileWritingJob[A <: CharSequence](content: A) extends AbstractJob[A, Unit](content)((a: A) => Future{
	Files.writeString(Paths.get("/home/og_pixel/filezzz"), content)
	()
})

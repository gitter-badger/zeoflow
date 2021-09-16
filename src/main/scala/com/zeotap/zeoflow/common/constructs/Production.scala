package com.zeotap.zeoflow.common.constructs

import com.zeotap.zeoflow.common.dsl.FlowDSLHelper._
import com.zeotap.zeoflow.common.types.{FlowUDF, Sink, Source, Transformation}

object Production {

  def e2eFlow[A, B](sources: List[Source[A]],
                 udfs: List[FlowUDF[B]] = List(),
                 transformations: List[Transformation[A]],
                 sinks: List[Sink[A]]): FreeFlowDSL[Unit] = {
    for {
      _ <- loadSources[A](sources)
      _ <- loadUserDefinedFunctions[B](udfs)
      _ <- runTransformations[A](transformations)
      _ <- writeToSinks[A](sinks)
    } yield sinks
  }

}
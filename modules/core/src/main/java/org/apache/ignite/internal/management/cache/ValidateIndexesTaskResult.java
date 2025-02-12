/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.ignite.internal.management.cache;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Map;
import java.util.UUID;
import org.apache.ignite.internal.util.typedef.internal.S;
import org.apache.ignite.internal.util.typedef.internal.U;
import org.apache.ignite.internal.visor.VisorDataTransferObject;

/**
 *
 */
public class ValidateIndexesTaskResult extends VisorDataTransferObject {
    /** */
    private static final long serialVersionUID = 0L;

    /** Exceptions. */
    private Map<UUID, Exception> exceptions;

    /** Results from cluster. */
    private Map<UUID, ValidateIndexesJobResult> results;

    /**
     * @param results Results.
     * @param exceptions Exceptions.
     */
    public ValidateIndexesTaskResult(Map<UUID, ValidateIndexesJobResult> results,
        Map<UUID, Exception> exceptions) {
        this.exceptions = exceptions;
        this.results = results;
    }

    /**
     * For externalization only.
     */
    public ValidateIndexesTaskResult() {
    }

    /**
     * @return Exceptions.
     */
    public Map<UUID, Exception> exceptions() {
        return exceptions;
    }

    /**
     * @return Results from cluster.
     */
    public Map<UUID, ValidateIndexesJobResult> results() {
        return results;
    }

    /** {@inheritDoc} */
    @Override protected void writeExternalData(ObjectOutput out) throws IOException {
        U.writeMap(out, exceptions);
        U.writeMap(out, results);
    }

    /** {@inheritDoc} */
    @Override protected void readExternalData(byte protoVer, ObjectInput in) throws IOException, ClassNotFoundException {
        exceptions = U.readMap(in);
        results = U.readMap(in);
    }

    /** {@inheritDoc} */
    @Override public String toString() {
        return S.toString(ValidateIndexesTaskResult.class, this);
    }
}

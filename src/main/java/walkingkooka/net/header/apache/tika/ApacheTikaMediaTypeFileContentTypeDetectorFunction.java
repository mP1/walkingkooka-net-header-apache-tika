/*
 * Copyright 2019 Miroslav Pokorny (github.com/mP1)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package walkingkooka.net.header.apache.tika;

import org.apache.tika.Tika;
import walkingkooka.Binary;
import walkingkooka.net.header.HeaderValueException;
import walkingkooka.net.header.MediaType;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * A {@link Function} that uses the filename and binary to detect the content type.
 */
final class ApacheTikaMediaTypeFileContentTypeDetectorFunction implements BiFunction<String, Binary, MediaType> {

    /**
     * Singleton
     */
    final static ApacheTikaMediaTypeFileContentTypeDetectorFunction INSTANCE = new ApacheTikaMediaTypeFileContentTypeDetectorFunction();

    private ApacheTikaMediaTypeFileContentTypeDetectorFunction() {
        super();
    }

    @Override
    public MediaType apply(final String filename,
                           final Binary binary) {
        Objects.requireNonNull(filename, "file");
        Objects.requireNonNull(binary, "binary");

        try (final InputStream inputStream = binary.inputStream()) {
            return MediaType.parse(this.tika.detect(inputStream, filename));
        } catch (final IOException cause) {
            throw new HeaderValueException("Failed to detect content type for " + filename + " " + binary);
        }
    }

    /**
     * Currently uses the default config.
     */
    final Tika tika = new Tika();

    @Override
    public String toString() {
        return this.tika.toString();
    }
}

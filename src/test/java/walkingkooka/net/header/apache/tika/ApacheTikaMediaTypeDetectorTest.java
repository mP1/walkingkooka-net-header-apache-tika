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

/*
 * Copyright 2018 Miroslav Pokorny (github.com/mP1)
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

import org.junit.jupiter.api.Test;
import walkingkooka.Binary;
import walkingkooka.ToStringTesting;
import walkingkooka.net.header.MediaType;
import walkingkooka.net.header.MediaTypeDetectorTesting2;
import walkingkooka.reflect.ClassTesting2;
import walkingkooka.reflect.JavaVisibility;
import walkingkooka.test.ResourceTesting;

import java.io.IOException;

final public class ApacheTikaMediaTypeDetectorTest implements MediaTypeDetectorTesting2<ApacheTikaMediaTypeDetector>,
        ClassTesting2<ApacheTikaMediaTypeDetector>,
        ResourceTesting,
        ToStringTesting<ApacheTikaMediaTypeDetector> {

    @Test
    public void testDetectWebpImage() throws IOException {
        this.detectAndCheck(
                "360px-RSPB_Dearne_Valley_Old_Moor_(cropped).webp",
                MediaType.parse("image/webp")
        );
    }

    @Test
    public void testDetectHtmlWebPage() throws IOException {
        this.detectAndCheck(
                "webpage.html",
                MediaType.TEXT_HTML
        );
    }

    @Test
    public void testDetectTextFile() throws IOException {
        this.detectAndCheck(
                "file.txt",
                MediaType.TEXT_PLAIN
        );
    }

    private void detectAndCheck(final String filename,
                                final MediaType expected) throws IOException {
        this.detectAndCheck(
                filename,
                Binary.with(
                        this.resourceAsBytes(
                                this.getClass(),
                                this.getClass().getSimpleName() + "/" + filename
                        )
                ),
                expected);
    }

    @Override
    public ApacheTikaMediaTypeDetector createMediaTypeDetector() {
        return ApacheTikaMediaTypeDetector.INSTANCE;
    }

    // ToString.........................................................................................................

    @Test
    public void testToString() {
        this.toStringAndCheck(
                ApacheTikaMediaTypeDetector.INSTANCE,
                ApacheTikaMediaTypeDetector.INSTANCE.tika.toString()
        );
    }

    // class............................................................................................................

    @Override
    public Class<ApacheTikaMediaTypeDetector> type() {
        return ApacheTikaMediaTypeDetector.class;
    }

    @Override
    public JavaVisibility typeVisibility() {
        return JavaVisibility.PACKAGE_PRIVATE;
    }
}

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
import walkingkooka.net.header.MediaType;
import walkingkooka.test.ClassTesting2;
import walkingkooka.test.ResourceTesting;
import walkingkooka.test.ToStringTesting;
import walkingkooka.type.JavaVisibility;
import walkingkooka.util.BiFunctionTesting;

import java.io.IOException;

final public class ApacheTikaMediaTypeFileContentTypeDetectorFunctionTest implements BiFunctionTesting<ApacheTikaMediaTypeFileContentTypeDetectorFunction, String, Binary, MediaType>,
        ClassTesting2<ApacheTikaMediaTypeFileContentTypeDetectorFunction>,
        ResourceTesting,
        ToStringTesting<ApacheTikaMediaTypeFileContentTypeDetectorFunction> {

    @Test
    public void testWebpImage() throws IOException {
        this.detectAndCheck("360px-RSPB_Dearne_Valley_Old_Moor_(cropped).webp", MediaType.parse("image/webp"));
    }

    @Test
    public void testHtmlWebPage() throws IOException {
        this.detectAndCheck("webpage.html", MediaType.TEXT_HTML);
    }

    @Test
    public void testTextFile() throws IOException {
        this.detectAndCheck("file.txt", MediaType.TEXT_PLAIN);
    }

    private void detectAndCheck(final String filename,
                                final MediaType expected) throws IOException {
        this.applyAndCheck(this.createBiFunction(),
                filename,
                Binary.with(this.resourceAsBytes(this.getClass(), this.getClass().getSimpleName() + "/" + filename)),
                expected);
    }

    @Test
    public void testToString() {
        this.toStringAndCheck(ApacheTikaMediaTypeFileContentTypeDetectorFunction.INSTANCE, ApacheTikaMediaTypeFileContentTypeDetectorFunction.INSTANCE.tika.toString());
    }

    @Override
    public ApacheTikaMediaTypeFileContentTypeDetectorFunction createBiFunction() {
        return ApacheTikaMediaTypeFileContentTypeDetectorFunction.INSTANCE;
    }

    @Override
    public Class<ApacheTikaMediaTypeFileContentTypeDetectorFunction> type() {
        return ApacheTikaMediaTypeFileContentTypeDetectorFunction.class;
    }

    @Override
    public JavaVisibility typeVisibility() {
        return JavaVisibility.PACKAGE_PRIVATE;
    }
}

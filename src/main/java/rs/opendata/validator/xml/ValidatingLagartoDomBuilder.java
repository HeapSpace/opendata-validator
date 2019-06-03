package rs.opendata.validator.xml;

import jodd.lagarto.LagartoParser;
import jodd.lagarto.dom.Document;
import jodd.lagarto.dom.LagartoDOMBuilder;
import jodd.lagarto.dom.LagartoDOMBuilderTagVisitor;

public class ValidatingLagartoDomBuilder extends LagartoDOMBuilder {
	@Override
	protected Document doParse(final LagartoParser lagartoParser) {
		lagartoParser.setConfig(config);

		final LagartoDOMBuilderTagVisitor domBuilderTagVisitor =
			new LagartoDOMBuilderTagVisitor(this) {
				@Override
				public void error(final String message) {
					throw new RuntimeException("XML parsing failed");
				}
			};

		lagartoParser.parse(domBuilderTagVisitor);

		return domBuilderTagVisitor.getDocument();
	}
}

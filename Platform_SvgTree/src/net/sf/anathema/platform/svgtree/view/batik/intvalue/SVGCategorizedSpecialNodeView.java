package net.sf.anathema.platform.svgtree.view.batik.intvalue;

import net.sf.anathema.framework.value.IIntValueView;
import net.sf.anathema.platform.svgtree.presenter.view.ISVGCategorizedSpecialNodeView;
import net.sf.anathema.platform.svgtree.view.batik.IBoundsCalculator;
import org.apache.batik.dom.svg.SVGDOMImplementation;
import org.apache.batik.dom.svg.SVGOMDocument;
import org.apache.batik.util.SVGConstants;
import org.w3c.dom.Element;
import org.w3c.dom.svg.SVGDocument;
import org.w3c.dom.svg.SVGGElement;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import static net.sf.anathema.platform.svgtree.document.components.ISVGCascadeXMLConstants.VALUE_COLOR_SVG_BLACK;

public class SVGCategorizedSpecialNodeView implements ISVGCategorizedSpecialNodeView {

  private final String nodeId;
  private final double nodeWidth;
  private final Color nodeColor;
  private final List<SVGIntValueView> categories = new ArrayList<SVGIntValueView>();
  private final int widthInDots;

  public SVGCategorizedSpecialNodeView(
      String nodeId,
      double nodeWidth,
      Color nodeColor,
      int widthInDots) {
    this.nodeId = nodeId;
    this.nodeWidth = nodeWidth;
    this.nodeColor = nodeColor;
    this.widthInDots = widthInDots;
  }

  @Override
  public String getNodeId() {
    return nodeId;
  }

  @Override
  public void setVisible(boolean visible) {
    for (SVGIntValueView view : categories) {
      view.setVisible(visible);
    }
  }

  @Override
  public SVGGElement initGui(SVGOMDocument document, IBoundsCalculator boundsCalculator) {
    SVGGElement groupElement = (SVGGElement) document.createElementNS(
        SVGDOMImplementation.SVG_NAMESPACE_URI,
        SVGConstants.SVG_G_TAG);
    groupElement.appendChild(createBorder(document));
    for (int index = 0; index < categories.size(); index++) {
      Element displayElement = categories.get(index).initGui(document, boundsCalculator);
      setAttribute(
          displayElement,
          SVGConstants.SVG_TRANSFORM_ATTRIBUTE,
          "translate(0," + index * SVGIntValueDisplay.getDisplayDiameter(nodeWidth, widthInDots) * 1.1 + ")"); //$NON-NLS-1$ //$NON-NLS-2$
      groupElement.appendChild(displayElement);
    }
    return groupElement;
  }

  private void setAttribute(org.w3c.dom.Element element, String attributeName, String attributeValue) {
    element.setAttributeNS(null, attributeName, attributeValue);
  }

  private Element createBorder(SVGDocument document) {
    Element rectangle = document.createElementNS(SVGDOMImplementation.SVG_NAMESPACE_URI, SVGConstants.SVG_RECT_TAG);
    int length = categories.size();
    double dotHeight = SVGIntValueDisplay.getDisplayDiameter(nodeWidth, widthInDots);
    double adjustment = 1.15;
    double height = length * dotHeight * adjustment;
    setAttribute(rectangle, SVGConstants.SVG_X_ATTRIBUTE, SVGConstants.SVG_ZERO_VALUE);
    setAttribute(rectangle, SVGConstants.SVG_Y_ATTRIBUTE, SVGConstants.SVG_ZERO_VALUE);
    setAttribute(rectangle, SVGConstants.SVG_WIDTH_ATTRIBUTE, String.valueOf(nodeWidth));
    setAttribute(rectangle, SVGConstants.SVG_HEIGHT_ATTRIBUTE, String.valueOf(height));
    setAttribute(rectangle, SVGConstants.SVG_STROKE_ATTRIBUTE, VALUE_COLOR_SVG_BLACK);
    setAttribute(rectangle, SVGConstants.SVG_FILL_ATTRIBUTE, VALUE_COLOR_SVG_BLACK);
    setAttribute(rectangle, SVGConstants.SVG_FILL_OPACITY_ATTRIBUTE, SVGConstants.SVG_ZERO_VALUE);
    return rectangle;
  }

  @Override
  public IIntValueView addCategory(String labelText, int maxValue, int value) {
    SVGIntValueView view = new SVGIntValueView(maxValue, widthInDots, nodeWidth, nodeColor, labelText, value);
    categories.add(view);
    return view;
  }

  @Override
  public void reset() {
    // Nothing to do
  }
}
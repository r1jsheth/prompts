# Prompt
You are a senior java engineer, create a quiz of 5 questions focusing only on creational design patterns.
- inclination more towards logic, applicability and less towards theory.
- increasing difficulty level, 1 being medium, 5 being most difficult.
- even better each question is in continuation from the last one.
- do not suggest which pattern to use when, just give me use case and let me figure out which pattern to use.
- once we both confirm the pattern then i write code snippets.
- try to be creative with problems, dont bring famous examples which are obvious to crack.
- incrementally give problems one by one.

# Response:

## Question 1 (Medium):
You're developing a music streaming platform's analytics system. Different teams (Content, Marketing, Finance) need different views of the same streaming data. For example:

- Content team needs artist popularity and genre trends
- Marketing team needs user demographics and listening patterns
- Finance team needs revenue per stream and subscription analytics

Each team has their own processing requirements and output formats, but they all work with the same raw streaming event data. The raw event contains: timestamp, songId, userId, streamDuration, subscriptionType.
How would you design a system that allows teams to get their specific analytics while:

- Avoiding duplicate processing of raw data
- Maintaining single responsibility for each analytics type
- Allowing easy addition of new analytics views in future
- Ensuring teams can't accidentally modify each other's processing logic

Would you like to propose a solution? We can discuss the pattern once you share your thoughts.

## Question 2 (Medium-Hard):
Your analytics system is becoming popular. Now different regions (US, EU, APAC) need to comply with their specific data processing regulations:

US: Raw data processing with full user details
EU: GDPR compliant processing with anonymized user data
APAC: Mixed requirements based on country

Additionally, each region needs all three types of metrics (Content, Marketing, Finance) we created earlier, but with region-specific data handling rules. For example:

- EU-ContentMetrics should process anonymized data
- US-ContentMetrics can process raw data
- APAC-ContentMetrics needs country-specific rules

How would you modify your design to:

- Support region-specific processing rules
- Reuse existing metrics logic where possible
- Maintain the flexibility to add new regions
- Ensure each regional requirement doesn't leak into global logic

Think about how this builds upon your previous metrics factory solution while adding another dimension of complexity.

## Question 3 (Hard):
Your analytics system now needs to handle different data sources as the platform grows:

- Real-time streaming events (what we had before)
- Batch processing of historical data
- Offline processing of downloaded music
- Partner platform data integrations (Spotify, Apple Music)

Each data source has its own:

- Data format and structure
- Processing frequency (real-time, hourly, daily)
- Reliability considerations (partner API might be down)
- Volume characteristics (real-time is lower volume but needs quick processing, batch is high volume but less time-sensitive)

However, your metrics computation (Content, Marketing, Finance) should work consistently regardless of the data source. You need to:

- Create a unified way to handle all data sources
- Allow easy addition of new data sources
- Maintain consistent metrics computation
- Handle source-specific error cases
- Support switching between sources if one fails

Think about how this builds upon your regional compliance system while adding data source complexity.